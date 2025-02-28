package com.org.proddaturiMinApp.service;

import com.org.proddaturiMinApp.model.User;
import com.org.proddaturiMinApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private static final int OTP_EXPIRY_MINUTES = 5; // OTP valid for 5 minutes
    private static final SecureRandom random = new SecureRandom();
    @Autowired
    UserRepository userrepo;
    public String generateOTP(long mobileno) {
        String otp = String.format("%06d", random.nextInt(1000000)); // Generate 6-digit OTP
        User userotp=new User(mobileno,null,otp,LocalDateTime.now());
        userrepo.save(userotp);
        return otp;
    }
//  public boolean validateOtp(long mobileno,String otp){
//        Optional<User> userOtps=userrepo.findBymobileno(mobileno);
//        System.out.println("User otp is"+userOtps);
//        if(userOtps.isPresent()){
//            User userEntity=userOtps.get();
//            String finalotp=userEntity.getOtp();
//            if(userEntity.getOtpExpiry().isBefore(LocalDateTime.now())){
//                userrepo.delete(userEntity); // OTP is valid, delete after use
//                return true;
//            }
//        }
//        return false;
//  }


    public boolean validateOtp(long mobileno, String otp) {
        Optional<User> userOtps = userrepo.findBymobileno(mobileno);

        if (userOtps.isPresent()) {
            User userEntity = userOtps.get();
            System.out.println("user Entity is"+userEntity);
            String finalOtp = userEntity.getOtp();
            System.out.println("otp is"+finalOtp);
            if (finalOtp != null && finalOtp.trim().equals(otp.trim())) {
                if (userEntity.getLdt().plusMinutes(OTP_EXPIRY_MINUTES).isBefore(LocalDateTime.now())) {
                    System.out.println("OTP has expired.");
                    return false;
                }
              userrepo.deleteByMobileNumber(mobileno); // Delete OTP after successful validation
                return true;
            }
        }
        return false;
    }


}
