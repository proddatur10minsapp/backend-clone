package com.org.proddaturiMinApp.service;

import com.org.proddaturiMinApp.utils.CommonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.org.proddaturiMinApp.model.User;
import com.org.proddaturiMinApp.repository.UserRepository;
import com.org.proddaturiMinApp.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
     // OTP valid for 5 minutes
    private static final SecureRandom random = new SecureRandom();
    static String otp = String.format("%06d", random.nextInt(1000000)); // Generate 6-digit OTP
    private static long userMobileNumber;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserUtils userUtils;
    @Autowired
    CommonProperties properties;
    private final int OTP_EXPIRY_MINUTES = properties.OtpExpireTime;
    // code for generate otp
    public String generateOtp(long mobileNumber) {
        userMobileNumber = mobileNumber;
        return otp;
    }

    // code for validate otp and save user
    public Boolean validateOtpAndSaveUser(String id, long mobileNumber, String otp) {
        String updatedId = userUtils.idIncrement(id);
        if (!userRepository.existsByMobileNumber(mobileNumber)) {
            if (validateOtp(mobileNumber, otp)) {
                User newUser = new User(updatedId, mobileNumber, null);
                userRepository.save(newUser);
                return true;
            }
        }
        return false;
    }

    // code for validate otp
    public boolean validateOtp(long mobileNumber, String userOtp) {
        String finalOtp = otp;
        if ((finalOtp != null && finalOtp.trim().equals(userOtp.trim())) && (mobileNumber == userMobileNumber)) {
            log.info("OTP validate successfully");
            return true;
        }
        return false;
    }

    // code for update user data
    public String updateUserData(long mobileNumber, String username) {
        Optional<User> userdata = userRepository.findByMobileNumber(mobileNumber);
        if (userdata.isPresent()) {
            User userData = userdata.get();
            userData.setUserName(username);
            userRepository.save(userData);
        }
        return username + properties.UserData;
    }

}




