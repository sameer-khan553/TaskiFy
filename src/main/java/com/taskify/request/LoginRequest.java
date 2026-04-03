package com.taskify.request;

import com.taskify.service.AuthService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter
@Setter
public class LoginRequest {


    @Autowired
    private AuthService authService;
    private String email;
    private String password;
    // getters + setters


    // Change controller to:
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequest req) {
        return authService.login(req.getEmail(), req.getPassword());

    }
}
