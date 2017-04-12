package com.findandplay.entity;

import com.findandplay.enums.AdStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "spaces")
public class SpaceEntity extends BaseEntity {
    private LocalDateTime created;
    @Enumerated(EnumType.STRING)
    private AdStatus status;
    private String address;
    private String info;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEntity author;

    @ManyToMany
    @JoinTable(name = "space_sports",
            joinColumns = {@JoinColumn(name = "space_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "sport_id", referencedColumnName = "id")})
    private Set<SportEntity> sports = new HashSet<>();
}