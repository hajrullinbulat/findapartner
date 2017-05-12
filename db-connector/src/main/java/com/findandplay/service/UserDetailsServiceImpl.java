package com.findandplay.service;

import com.findandplay.entity.UserEntity;
import com.findandplay.jpaService.UserService;
import com.findandplay.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String msisdn) throws UsernameNotFoundException {
        UserEntity user = userService.findByMsisdn(msisdn);
        Optional.ofNullable(user)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with msisdn %s does not exist!", msisdn)));
        return UserUtil.convert(user);

    }

}
