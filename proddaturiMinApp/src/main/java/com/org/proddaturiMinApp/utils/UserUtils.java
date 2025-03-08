package com.org.proddaturiMinApp.utils;

import com.org.proddaturiMinApp.model.User;
import com.org.proddaturiMinApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUtils {
    @Autowired
    private UserRepository repository;
    public String idIncrement(String userId){
        List<User> user= repository.findAll();
        System.out.println("user details "+user);
        String updatedId=userId;
        for (User details : user) {
            System.out.println("details in for loop"+details);
            if (details.getId().equals(updatedId)) {
                updatedId = Integer.toString(Integer.parseInt(updatedId) + 1);
            }
        }
        System.out.println("user id "+updatedId);
        return updatedId;
    }
}