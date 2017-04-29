package com.findandplay.controller;

import com.findandplay.dto.PrincipalUser;
import com.findandplay.dto.UserDTO;
import com.findandplay.jdbcRepository.UserJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hajrullinbulat on 29.04.17.
 */
@RestController
public class MainController {
    private final UserJDBCRepository userJDBCRepository;

    @Autowired
    public MainController(UserJDBCRepository userJDBCRepository) {
        this.userJDBCRepository = userJDBCRepository;
    }

    @GetMapping("/mainTest")
    @ResponseBody
    public ResponseEntity loadMain(@AuthenticationPrincipal PrincipalUser user) {
        UserDTO userWithCheckedAdverts = userJDBCRepository.getUserWithCheckedAdverts(user.getMsisdn());
        return ResponseEntity.ok(userWithCheckedAdverts);
    }


}
