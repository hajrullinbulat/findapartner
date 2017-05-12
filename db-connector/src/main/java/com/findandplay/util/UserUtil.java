package com.findandplay.util;

import com.findandplay.dto.ImageDTO;
import com.findandplay.dto.PrincipalUser;
import com.findandplay.entity.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Optional;
import java.util.stream.Collectors;

public class UserUtil {
    public static PrincipalUser convert(UserEntity user) {
        return PrincipalUser.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .msisdn(user.getMsisdn())
                .confirmed(user.isConfirmed())
                .key(user.getKey())
                .created(user.getCreated())
                .lastAction(user.getLastAction())
                .lastPasswordReset(user.getLastPasswordReset())
                .city(user.getCity())
                .sports(user.getSports())
                .roles(
                        user.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                                .collect(Collectors.toSet())
                )
                .avatar(Optional.ofNullable(user.getAvatar())
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
