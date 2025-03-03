package com.org.proddaturiMinApp.repository;

import com.org.proddaturiMinApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,Integer> {
    Optional<User> findBymobileno(long mobileno);
    boolean existsByMobileno(long mobileno);


//    @Transactional
//    @Query(value = "{mobileno:?0}",delete = true)
//    void deleteByMobileNumber(long mobile_no);
}
