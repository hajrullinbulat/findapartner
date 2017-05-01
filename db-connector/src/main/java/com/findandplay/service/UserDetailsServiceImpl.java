package com.findandplay.service;

import com.findandplay.dto.ImageDTO;
import com.findandplay.dto.PrincipalUser;
import com.findandplay.entity.UserEntity;
import com.findandplay.jpaRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //todo делегировать
    @Override
    public UserDetails loadUserByUsername(String msisdn) throws UsernameNotFoundException {
        UserEntity dbUser = userRepository.findByMsisdn(msisdn);
        Optional.ofNullable(dbUser)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with msisdn %s does not exist!", msisdn)));
        return PrincipalUser.builder()
                .name(dbUser.getName())
                .surname(dbUser.getSurname())
                .email(dbUser.getEmail())
                .msisdn(dbUser.getMsisdn())
                .pass(dbUser.getPassword())
                .confirmed(dbUser.isConfirmed())
                .key(dbUser.getKey())
                .created(dbUser.getCreated())
                .lastAction(dbUser.getLastAction())
                .city(dbUser.getCity())
                .sports(dbUser.getSports())
                .roles(
                        dbUser.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                                .collect(Collectors.toSet())
                )
                .avatar(Optional.ofNullable(dbUser.getAvatar())
                        .map(avatar ->
                                ImageDTO.builder()
                                        .low(avatar.getLow())
                                        .medium(avatar.getMedium())
                                        .high(avatar.getHigh())
                                        .build())
                        .orElse(ImageDTO.empty()))
                .build();

    }

}
