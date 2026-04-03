package com.taskify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/auth/login"; // Redirect root to login page
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Serve dashboard template
    }
}