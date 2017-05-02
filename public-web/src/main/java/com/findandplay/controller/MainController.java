package com.findandplay.controller;

import com.findandplay.dto.PrincipalUser;
import com.findandplay.dto.UserDTO;
import com.findandplay.enums.SportType;
import com.findandplay.jdbcRepository.UserJDBCRepository;
import com.findandplay.service.AdvertJDBCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hajrullinbulat on 29.04.17.
 */
@RestController
public class MainController {
    private final UserJDBCRepository userJDBCRepository;
    private final AdvertJDBCService advertJDBCService;

    @Autowired
    public MainController(
            UserJDBCRepository userJDBCRepository,
            AdvertJDBCService advertJDBCService
    ) {
        this.userJDBCRepository = userJDBCRepository;
        this.advertJDBCService = advertJDBCService;
    }

    @GetMapping("/mainTest")
    public ResponseEntity loadMain(@AuthenticationPrincipal PrincipalUser user) {
        UserDTO userWithCheckedAdverts = userJDBCRepository.getUserWithCheckedAdverts(user.getMsisdn());
        return ResponseEntity.ok(userWithCheckedAdverts);
    }

    //todo обдумать
    @GetMapping("/find")
    public ResponseEntity search(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page
    ) {
        return ResponseEntity.ok(advertJDBCService.getAdverts(SportType.FOOTBALL, page));
    }


}
