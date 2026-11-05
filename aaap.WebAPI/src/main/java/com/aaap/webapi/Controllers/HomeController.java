package com.aaap.webapi.Controllers;

import com.aaap.Model.Models.Letter;
import com.aaap.Service.Abstractions.ILetterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {
    private final ILetterService letterService;

    public HomeController(ILetterService letterService){
        this.letterService = letterService;
    }

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/letter")
    public String letter(Model model) {
        Letter letter = letterService.getLetter();
        model.addAttribute("letter", letter);
        return "letter";
    }

    @GetMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    @GetMapping("/together")
    public String together() {
        return "together";
    }
}
