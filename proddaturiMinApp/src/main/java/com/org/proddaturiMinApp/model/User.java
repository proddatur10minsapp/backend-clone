package com.org.proddaturiMinApp.model;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "proddaturiwebApp")
public class User {
    private long mobileno;
    private String username;
    private String otp;
    private LocalDateTime ldt;
}
