package com.findandplay.jpaService;

import com.findandplay.entity.UserEntity;
import com.findandplay.jpaRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findByMsisdn(String msisdn) {
        return userRepository.findByMsisdn(msisdn);
    }

}
