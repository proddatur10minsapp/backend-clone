package com.org.proddaturiMinApp.repository;

import com.org.proddaturiMinApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findByMobileNumber(long mobileNumber);

    boolean existsByMobileNumber(long mobileNumber);
}

