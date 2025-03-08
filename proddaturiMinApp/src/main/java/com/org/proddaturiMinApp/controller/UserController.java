package com.org.proddaturiMinApp.controller;

import com.org.proddaturiMinApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/authentication")
public class UserController {

    @Autowired
    UserService userservice;

    //  generate otp
    @GetMapping("/generate")
    public String generate(@RequestParam long mobileNumber) {
        return userservice.generateOtp(mobileNumber);
    }

    //  validate and save user
    @PostMapping("/save")
    public String validateAndSaveUser(@RequestParam String id, @RequestParam long mobileNumber, @RequestParam String otp) {
        Boolean userResponse = userservice.validateOtpAndSaveUser(id, mobileNumber, otp);
        if (userResponse) return "login success with " + mobileNumber + "number";
        else return "login unsuccessful with " + mobileNumber + "number";
    }


    @PostMapping("/updateUser")
    public String updateUserInfo(@RequestParam long mobileNumber, @RequestParam String userName) {
        String userinfo = userservice.updateUserData(mobileNumber, userName);
        return userName + " updated successfully";
    }

}
