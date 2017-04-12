package com.findandplay.entity;

import com.findandplay.enums.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "roles")
public class RoleEntity extends BaseEntity implements GrantedAuthority {
    @Enumerated(EnumType.STRING)
    private RoleTypes name;

    public RoleEntity(RoleTypes type, Long id) {
        setId(id);
        this.name = type;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }
}