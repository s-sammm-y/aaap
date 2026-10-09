
package com.aaap.webapi.Controllers;

import com.aaap.Model.Models.KissGame;
import com.aaap.Service.Abstractions.IKissGameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kiss-game")
public class KissGameController {

    private static final String GAME_KEY = "kissGame";

    private final IKissGameService kissGameService;

    public KissGameController(IKissGameService kissGameService) {
        this.kissGameService = kissGameService;
    }

    @GetMapping
    public String getKissGame(HttpSession session, Model model) {
        model.addAttribute("game", getOrCreateGame(session));
        return "kiss-game";
    }

    @PostMapping("/move")
    public String move(
            @RequestParam String direction,
            HttpSession session,
            Model model) {

        KissGame game = getOrCreateGame(session);
        kissGameService.move(game, direction);

        model.addAttribute("game", game);
        return "kiss-game :: gameContent";
    }

    @PostMapping("/reset")
    public String reset(HttpSession session, Model model) {
        KissGame game = getOrCreateGame(session);
        kissGameService.reset(game);

        model.addAttribute("game", game);
        return "kiss-game :: gameContent";
    }

    private KissGame getOrCreateGame(HttpSession session) {
        KissGame game = (KissGame) session.getAttribute(GAME_KEY);

        if (game == null) {
            game = new KissGame();
            session.setAttribute(GAME_KEY, game);
        }

        return game;
    }
}
