package com.findandplay.service;

import com.findandplay.entity.UserEntity;
import com.findandplay.jpaRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String msisdn) throws UsernameNotFoundException {
        UserEntity user = Optional.ofNullable(userRepository.findByMsisdn(msisdn))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s does not exist!", msisdn)));
        return new UserRepositoryUserDetails(user);
    }

    private final static class UserRepositoryUserDetails extends UserEntity implements UserDetails {
        private UserRepositoryUserDetails(UserEntity user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return getRoles();
        }

        @Override
        public String getUsername() {
            return getMsisdn();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return isConfirmed();
        }
    }
}
