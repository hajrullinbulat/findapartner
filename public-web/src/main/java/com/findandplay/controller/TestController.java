package com.findandplay.controller;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.findandplay.dto.PrincipalUser;
import com.findandplay.dto.UserDTO;
import com.findandplay.entity.AdvertEntity;
import com.findandplay.entity.RoleEntity;
import com.findandplay.entity.SportEntity;
import com.findandplay.entity.UserEntity;
import com.findandplay.enums.*;
import com.findandplay.jdbcRepository.UserJDBCRepository;
import com.findandplay.jpaRepository.AdvertRepository;
import com.findandplay.jpaRepository.CheckedAdverts;
import com.findandplay.jpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hajrullinbulat on 11.03.17.
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;
    private final CheckedAdverts checkedAdverts;
    private final UserJDBCRepository userJDBCRepository;

    @GetMapping("/")
    @ResponseBody
    public String jpaGet() {
        return "test";
    }

    @GetMapping("/signup")
    @ResponseBody
    public ResponseEntity testInit() {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(new RoleEntity(RoleTypes.USER, 1L));

        UserEntity user = UserEntity.builder()
                .name("Булат")
                .surname("Хайруллин")
                .email("hajrullinbulat@gmail.com")
                .created(LocalDateTime.now())
                .msisdn("79991697612")
                .city(City.KAZAN)
                .confirmed(true)
                .password(new BCryptPasswordEncoder(4).encode("Qwerty"))
                .roles(roles).build();
        userRepository.save(user);

        SportEntity sport = SportEntity.builder().name(SportType.FOOTBALL).build();
        AdvertEntity advert = AdvertEntity.builder()
                .count(1)
                .created(LocalDateTime.now())
                .info("Some text about")
                .level(Skill.NEW)
                .status(AdStatus.OPEN)
                .author(user)
                .sport(sport).build();
        advertRepository.save(advert);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity get() {
        UserEntity one = userRepository.findByMsisdn("79991697612");
        return ResponseEntity.ok(one);
    }

    @GetMapping("/get1")
    @ResponseBody
    public ResponseEntity get1() {
        AdvertEntity one = advertRepository.findAdvertById(74L, EntityGraphUtils.fromName("getAdvert"));
//        UserEntity one1 = userRepository.findOne(79L);
//
//
//        CheckedAdvertByUserEntity checkedAdvertByUserEntity = new CheckedAdvertByUserEntity(one1, one, LocalDateTime.now(), CheckStatus.WAITING);
//        checkedAdverts.save(checkedAdvertByUserEntity);

        return ResponseEntity.ok(one);
    }

    @GetMapping("/get2")
    @ResponseBody
    public ResponseEntity get2(@AuthenticationPrincipal PrincipalUser user) {
        UserDTO userWithCreatedAndCheckedSections = userJDBCRepository.getUserWithCreatedAndCheckedSections(user.getMsisdn());
        return ResponseEntity.ok(user.getMsisdn());
    }
}
