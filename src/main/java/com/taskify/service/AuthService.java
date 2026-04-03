package com.taskify.service;

import com.taskify.model.User;
import com.taskify.repository.UserRepository;
import com.taskify.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String signup(User user) {

        ///   check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists!";
        }

        ///   encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        /// save user
        userRepository.save(user);
        return "User registered successfully!";
    }


    public String login(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return "User not found!";
        }

        User user = userOptional.get();

        // check password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid password!";
        }

        // generate token
        return jwtService.generateToken(user.getEmail());
    }
}
