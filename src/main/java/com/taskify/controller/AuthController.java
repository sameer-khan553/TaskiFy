package com.taskify.controller;

import com.taskify.model.User;
import com.taskify.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    @ResponseBody // 🔥 needed for API
    public String signup(@RequestBody User user) {
        return authService.signup(user);
    }

    @PostMapping("/login")
    @ResponseBody // 🔥 needed for API
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        return authService.login(email, password);
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // loads dashboard.html
    }

    @GetMapping("/")
    public String signupPage() {
        return "signup"; // loads signup.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}