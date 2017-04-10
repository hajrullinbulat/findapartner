package com.findandplay.controller;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.findandplay.entity.AdvertEntity;
import com.findandplay.repository.AdvertRepository;
import com.findandplay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hajrullinbulat on 11.03.17.
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;

    @GetMapping("/")
    @ResponseBody
    public String jpaGet() {
        return "test";
    }

    @GetMapping("/initDB")
    @ResponseBody
    public ResponseEntity testInit() {
//        UserEntity user = UserEntity.builder()
//                .name("Булат")
//                .surname("Хайруллин")
//                .email("hajrullinbulat@gmail.com")
//                .created(LocalDateTime.now())
//                .msisdn("79991697612")
//                .city(City.KAZAN)
//                .confirmed(false).build();
//        userRepository.save(user);
//
//        SportEntity sport = SportEntity.builder().name(SportType.FOOTBALL).build();
//        AdvertEntity advert = AdvertEntity.builder()
//                .count(1)
//                .created(LocalDateTime.now())
//                .info("Some text about")
//                .level(Skill.NEW)
//                .status(AdStatus.OPEN)
//                .author(user)
//                .sport(sport).build();
//        advertRepository.save(advert);

        AdvertEntity one = advertRepository.findAdvertById(54L, EntityGraphUtils.fromName("getAdvert"));

        return ResponseEntity.ok(one);
    }
}
