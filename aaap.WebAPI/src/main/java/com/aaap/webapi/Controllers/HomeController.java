package com.aaap.webapi.Controllers;

import com.aaap.Model.Models.Memory;
import com.aaap.Service.Abstractions.IMemoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/letter")
    public String letter() {
        return "letter";
    }
}
