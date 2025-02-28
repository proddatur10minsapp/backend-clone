package com.org.proddaturiMinApp.controller;

import com.org.proddaturiMinApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/otp")
public class UserController {

    @Autowired
    UserService userservice;
    @PostMapping("/generate")
    public Map<String,String> generate(@RequestParam long mobileno){
        String otp= userservice.generateOTP(mobileno);
        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP sent successfully!");
        response.put("otp", otp); // In a real time app, we have to send the send OTP via email/SMS
        return response;
    }

    @GetMapping("/validate")
    public Map<String, String> validate(@RequestParam long mobileno, @RequestParam String otp) {
        boolean isValid = userservice.validateOtp(mobileno, otp);
        Map<String, String> response = new HashMap<>();
        response.put("message", isValid ? "OTP is valid" : "Invalid or expired OTP");
        return response;
    }

}
