package com.aaap.webapi.Controllers;

import com.aaap.Model.Models.Question;
import com.aaap.Service.Abstractions.IQAGameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QAGameController {

    private final IQAGameService qaGameService;

    public QAGameController(IQAGameService qaGameService) {
        this.qaGameService = qaGameService;
    }

    private void initializeGame(HttpSession session) {

        session.setAttribute("qaIndex", 0);
        session.setAttribute("correctCount", 0);
        session.setAttribute("incorrectAttempts", 0);
    }

    @GetMapping("/qa-game")
    public String showGame(HttpSession session, Model model) {

        if (session.getAttribute("qaIndex") == null) {
            initializeGame(session);
        }

        List<Question> questions = qaGameService.getQuestions();

        int index = (int) session.getAttribute("qaIndex");

        if (index >= questions.size()) {
            return "redirect:/qa-game/result";
        }

        model.addAttribute("question", questions.get(index));
        model.addAttribute("questionNumber", index + 1);
        model.addAttribute("totalQuestions", questions.size());

        model.addAttribute("feedback",
                session.getAttribute("qaFeedback"));

        model.addAttribute("feedbackType",
                session.getAttribute("qaFeedbackType"));

        session.removeAttribute("qaFeedbackType");

        session.removeAttribute("qaFeedback");

        return "qa-game";
    }

    @PostMapping("/qa-game/answer")
    public String submitAnswer(
            @RequestParam(
                    value = "selectedAnswers",
                    required = false
            ) List<String> selectedAnswers,
            HttpSession session) {

        if (session.getAttribute("qaIndex") == null) {
            return "redirect:/qa-game";
        }

        List<Question> questions = qaGameService.getQuestions();

        int index = (int) session.getAttribute("qaIndex");

        if (index >= questions.size()) {
            return "redirect:/qa-game/result";
        }

        Question question = questions.get(index);

        boolean correct = qaGameService.validateAnswer(
                question,
                selectedAnswers
        );

        if (selectedAnswers == null || selectedAnswers.isEmpty()) {
            session.setAttribute(
                    "qaFeedback",
                    "Please select at least one answer."
            );

            return "redirect:/qa-game";
        }

        if (correct) {

            int count = (int) session.getAttribute("correctCount");

            session.setAttribute("correctCount", count + 1);

            session.setAttribute("qaFeedback",
                    "Thik Bolecho BBGGGGGGGGGggggggg...... ❤️");

            session.setAttribute("qaFeedbackType", "success");

            session.setAttribute("qaIndex", index + 1);

        } else {

            int attempts = (int) session.getAttribute(
                    "incorrectAttempts"
            );

            session.setAttribute(
                    "incorrectAttempts",
                    attempts + 1
            );

            session.setAttribute("qaFeedback",
                    "Bhul Bolecho, amai arrr kau bhalo basenaaa.");

            session.setAttribute("qaFeedbackType", "error");

            session.setAttribute("qaIndex", index + 1);
        }

        if (correct && index + 1 >= questions.size()) {
            return "redirect:/qa-game/result";
        }

        return "redirect:/qa-game";
    }

    @GetMapping("/qa-game/result")
    public String showResult(
            HttpSession session,
            Model model) {

        model.addAttribute(
                "correctCount",
                session.getAttribute("correctCount")
        );

        model.addAttribute(
                "incorrectAttempts",
                session.getAttribute("incorrectAttempts")
        );

        model.addAttribute(
                "totalQuestions",
                qaGameService.getQuestions().size()
        );

        return "qa-result";
    }

    @PostMapping("/qa-game/restart")
    public String restartGame(HttpSession session) {

        initializeGame(session);

        return "redirect:/qa-game";
    }
}
