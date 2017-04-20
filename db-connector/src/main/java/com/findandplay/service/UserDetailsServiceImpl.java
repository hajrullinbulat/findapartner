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
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String msisdn) throws UsernameNotFoundException {
        UserEntity user = Optional.ofNullable(userRepository.findByMsisdn(msisdn))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with msisdn %s does not exist!", msisdn)));
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

        /**
         * @return флаг, что срок действия аккаунта еще не истек, он активен
         */
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        /**
         * @return флаг, что пользователь не заблокирован администраторами сайта
         */
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        /**
         * @return флаг, что срок действия пароля еще не истек, он активен
         */
        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        /**
         * @return флаг, что пользователь включен и подтвержден
         */
        @Override
        public boolean isEnabled() {
            return isConfirmed();
        }
    }
}
