package com.org.proddaturiMinApp.service;

import com.org.proddaturiMinApp.model.User;
import com.org.proddaturiMinApp.repository.UserRepository;
import com.org.proddaturiMinApp.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final int OTP_EXPIRY_MINUTES = 5; // OTP valid for 5 minutes
    private static final SecureRandom random = new SecureRandom();
    static String otp = String.format("%06d", random.nextInt(1000000)); // Generate 6-digit OTP
    @Autowired
    UserRepository userrepo;
    @Autowired
    private UserUtils userUtils;

    public String saveUser(long mobileno, String id) {
        String updatedId = userUtils.idIncrement(id);
        if (!userrepo.existsByMobileno(mobileno)) {
            User newUser = new User(updatedId, mobileno, null);
            userrepo.save(newUser);
        }
        return otp;
    }

    public boolean validateOtp(String userotp) {
        String finalOtp = otp;
        System.out.println("otp is" + finalOtp);
        if (finalOtp != null && finalOtp.trim().equals(userotp.trim())) {
            System.out.println("OTP validate successfully");
            return true;
        }
        return false;
    }

    public String updateUserData(long mobileno, String username) {
        Optional<User> userdata = userrepo.findBymobileno(mobileno);
        if (userdata.isPresent()) {
            User userno = userdata.get();
            userno.setUsername(username);
            userrepo.save(userno);
        }
        return "user data saved";
    }

}




