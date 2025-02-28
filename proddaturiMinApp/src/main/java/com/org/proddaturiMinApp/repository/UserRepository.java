package com.org.proddaturiMinApp.repository;

import com.org.proddaturiMinApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,Integer> {
    Optional<User> findBymobileno(long mobileno);
    @Transactional
    @Query(value = "{mobileno:?0}",delete = true)
    void deleteByMobileNumber(long mobile_no);
}
