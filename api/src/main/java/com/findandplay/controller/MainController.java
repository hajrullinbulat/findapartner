package com.findandplay.controller;

import com.findandplay.dto.PrincipalUser;
import com.findandplay.dto.UserDTO;
import com.findandplay.enums.SportType;
import com.findandplay.jdbcService.AdvertJDBCService;
import com.findandplay.jdbcService.UserJDBCService;
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
    private final UserJDBCService userJDBCService;
    private final AdvertJDBCService advertJDBCService;

    @Autowired
    public MainController(
            UserJDBCService userJDBCService,
            AdvertJDBCService advertJDBCService
    ) {
        this.userJDBCService = userJDBCService;
        this.advertJDBCService = advertJDBCService;
    }

    @GetMapping("/mainTest")
    public ResponseEntity loadMain(@AuthenticationPrincipal PrincipalUser user) {
        UserDTO userWithCheckedAdverts = userJDBCService.getUserWithCheckedAdverts(user.getMsisdn());
        return ResponseEntity.ok(userWithCheckedAdverts);
    }

    @GetMapping("/find/advert")
    public ResponseEntity searchAdverts(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "sport") SportType sport
    ) {
        return ResponseEntity.ok(advertJDBCService.getAdverts(sport, page));
    }

    @GetMapping("/find/section")
    public ResponseEntity searchSections(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "sport") SportType sport

    ) {
        return ResponseEntity.ok(advertJDBCService.getAdverts(sport, page));
    }

    @GetMapping("/find/space")
    public ResponseEntity searchSpaces(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "sport") SportType sport

    ) {
        return ResponseEntity.ok(advertJDBCService.getAdverts(sport, page));
    }


}
