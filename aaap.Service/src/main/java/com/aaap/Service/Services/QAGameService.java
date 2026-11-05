package com.aaap.Service.Services;

import com.aaap.Model.Models.Question;
import com.aaap.Service.Abstractions.IQAGameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QAGameService implements IQAGameService {

    @Override
    public List<Question> getQuestions() {

        return List.of(

                new Question(
                        "My favourite Drink??????",
                        List.of(
                                "Water",
                                "Alcohol",
                                "Fresh Lime",
                                "Milk Shake"
                        ),
                        Set.of("Fresh Lime")
                ),

                new Question(
                        "Doo you mishhhhhhhhhh meee????????",
                        List.of(
                                "Yes",
                                "Onekkkkkk",
                                "Akdom naaaaaa",
                                "Marboooooo"
                        ),
                        Set.of("Onekkkkkk")
                ),

                new Question(
                        "I'm Built like A?",
                        List.of(
                                "Brick",
                                "Option A",
                                "Option B",
                                "Option C"
                        ),
                        Set.of("Brick",
                                "Option A",
                                "Option B",
                                "Option C")
                ),

                new Question(
                        "2 + 2?",
                        List.of(
                                "22",
                                "2",
                                "3",
                                "Jetaii bolbe bhul hobe ehehe"
                        ),
                        Set.of("naN")
                ),

                new Question(
                        "My favourite Interest",
                        List.of(
                                "Cars",
                                "Dogs",
                                "Football",
                                "Games"
                        ),
                        Set.of("Cars")
                ),

                new Question(
                        "Will you stay with me forever?",
                        List.of(
                                "Yes",
                                "No"
                        ),
                        Set.of("Yes", "No")
                )

                // Add the remaining questions here.
        );
    }

    @Override
    public boolean validateAnswer(
            Question question,
            List<String> selectedAnswers) {

        if (selectedAnswers == null || selectedAnswers.isEmpty()) {
            return false;
        }

        return selectedAnswers.stream()
                .anyMatch(question.getCorrectOptions()::contains);
    }
}
