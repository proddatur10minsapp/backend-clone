package com.org.proddaturiMinApp.controller;

import com.org.proddaturiMinApp.model.User;
import com.org.proddaturiMinApp.repository.UserRepository;
import com.org.proddaturiMinApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/otp")
public class UserController {

    @Autowired
    UserService userservice;

    @Autowired
    UserRepository userrepo;

//    @Autowired
//    User user;
    @GetMapping("/getusers")
    public List<User> getAllUsers(){
        return userrepo.findAll();
    }

    @PostMapping("/generate")
    public Map<String,String> generate(@RequestParam long mobileno,@RequestParam String id){
        String otp= userservice.saveUser(mobileno,id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP sent successfully!");
        response.put("otp", otp); // In a real time app, we have to send the send OTP via email/SMS
        return response;
    }

    @GetMapping("/validate")
    public Map<String, String> validate( @RequestParam String otp) {
        boolean isValid = userservice.validateOtp(otp);
        Map<String, String> response = new HashMap<>();
        response.put("message", isValid ? "OTP is valid" : "Invalid or expired OTP");
        return response;
    }

    @PostMapping("/updateUser")
    public String updateUserInfo(@RequestParam long mobileno,@RequestParam String username){
       String userinfo=userservice.updateUserData(mobileno, username);
        return "Username updated successfully" ;
    }

}
