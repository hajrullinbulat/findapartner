package com.findandplay.jdbcService;

import com.findandplay.dto.UserDTO;
import com.findandplay.jdbcRepository.UserJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hajrullinbulat on 30.04.17.
 */
@Service
public class UserJDBCService {
    private final UserJDBCRepository userJDBCRepository;

    @Autowired
    public UserJDBCService(
            UserJDBCRepository userJDBCRepository
    ) {
        this.userJDBCRepository = userJDBCRepository;
    }

    public UserDTO getUserWithCreatedAndCheckedSections(String msisdn) {
        return userJDBCRepository.getUserWithCreatedAndCheckedSections(msisdn);
    }

    public UserDTO getUserWithCheckedAdverts(String msisdn) {
        return userJDBCRepository.getUserWithCheckedAdverts(msisdn);
    }

}
