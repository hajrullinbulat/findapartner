package com.findandplay.dto;

import com.findandplay.enums.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrincipalUser implements UserDetails {
    private String name;
    private String surname;
    private String email;
    private String msisdn;
    private String pass;
    private boolean confirmed;
    private String key;
    private LocalDateTime created;
    private LocalDateTime lastAction;
    private City city;
    private ImageDTO avatar;

    private Set<SimpleGrantedAuthority> roles = new HashSet<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public String getPassword() {
        return this.getPass();
    }

    /**
     * Логин (имя) пользователя
     */
    @Override
    public String getUsername() {
        return this.getMsisdn();
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
        return this.isConfirmed();
    }
}
