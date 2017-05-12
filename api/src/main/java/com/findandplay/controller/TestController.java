package com.findandplay.controller;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.findandplay.configuration.other.JwtTokenUtil;
import com.findandplay.dto.PrincipalUser;
import com.findandplay.dto.UserDTO;
import com.findandplay.entity.AdvertEntity;
import com.findandplay.entity.UserEntity;
import com.findandplay.enums.City;
import com.findandplay.enums.Skill;
import com.findandplay.enums.SportType;
import com.findandplay.jdbcRepository.UserJDBCRepository;
import com.findandplay.jpaRepository.AdvertRepository;
import com.findandplay.jpaRepository.CheckedAdverts;
import com.findandplay.jpaRepository.SpaceRepository;
import com.findandplay.jpaRepository.UserRepository;
import com.findandplay.json.UserSportJson;
import com.findandplay.json.UserSportsJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hajrullinbulat on 11.03.17.
 */
@Controller
public class TestController {
    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;
    private final SpaceRepository spaceRepository;
    private final CheckedAdverts checkedAdverts;
    private final UserJDBCRepository userJDBCRepository;
    private final Integer passStrong;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    public TestController(
            UserRepository userRepository,
            AdvertRepository advertRepository,
            SpaceRepository spaceRepository,
            CheckedAdverts checkedAdverts,
            UserJDBCRepository userJDBCRepository,
            @Value("${password.strength}") Integer passStrong,
            JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
        this.spaceRepository = spaceRepository;
        this.checkedAdverts = checkedAdverts;
        this.userJDBCRepository = userJDBCRepository;
        this.passStrong = passStrong;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    @ResponseBody
    public String jpaGet() {
        return "test";
    }

    @GetMapping("/signup")
    @ResponseBody
    public ResponseEntity testInit() {
//        Set<RoleEntity> roles = new HashSet<>();
//        roles.add(new RoleEntity(RoleTypes.USER, 1L));

        List<UserSportJson> userSportJson = new ArrayList<>();
        userSportJson.add(new UserSportJson(SportType.FOOTBALL.name(), Skill.PROFESSIONAL));
        userSportJson.add(new UserSportJson(SportType.FOOTBALL.name(), Skill.NEW));
        userSportJson.add(new UserSportJson(SportType.FOOTBALL.name(), Skill.AMATEUR));

        UserSportsJson userSportsJson = new UserSportsJson(userSportJson);

        UserEntity user = UserEntity.builder()
                .name("Булат")
                .surname("Хайруллин")
                .email("hajrullinbulat@gmail.com")
                .created(LocalDateTime.now())
                .msisdn("79991697612")
                .city(City.KAZAN)
                .confirmed(true)
                .password(new BCryptPasswordEncoder(passStrong).encode("Qwerty"))
                .sports(userSportsJson)
                .build();
        userRepository.save(user);

        UserEntity one = userRepository.findOne(73L);
//
//        List<SportType> sports = new ArrayList<>();
//        sports.add(SportType.FOOTBALL);
//        sports.add(SportType.VOLLEYBALL);
//        SpaceEntity space = SpaceEntity.builder()
//                .address("Нурсултана Назарбаева 75")
//                .sports(new SpaceSportsJson(sports))
//                .build();
//
//        spaceRepository.save(space);

        return ResponseEntity.ok(one);
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
//        CheckedAdvertByUserEntity checkedAdvertByUserEntity = new CheckedAdvertByUserEntity(one1, one, LocalDateTime.now(), CheckStatus.WAITING);
//        checkedAdverts.save(checkedAdvertByUserEntity);

        return ResponseEntity.ok(one);
    }

    @GetMapping("/get2")
    @ResponseBody
    public ResponseEntity get2(@AuthenticationPrincipal PrincipalUser user) {
        UserDTO userWithCreatedAndCheckedSections = userJDBCRepository.getUserWithCreatedAndCheckedSections(user.getMsisdn());
        return ResponseEntity.ok(userWithCreatedAndCheckedSections);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public PrincipalUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (PrincipalUser) userDetailsService.loadUserByUsername(username);
    }

    @RequestMapping(value = "/user1", method = RequestMethod.GET)
    @ResponseBody
    public PrincipalUser getAuthenticatedUser(@AuthenticationPrincipal PrincipalUser user) {
        return user;
    }
}
