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
    @Column(name = "space_created")
    private LocalDateTime created;

    @Column(name = "space_last_action")
    private LocalDateTime lastActionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "space_status")
    private AdStatus status;

    @Column(name = "space_address")
    private String address;

    @Column(name = "space_info")
    private String info;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "space_author_id")
    private UserEntity author;

    @ManyToMany
    @JoinTable(name = "space_sports",
            joinColumns = {@JoinColumn(name = "space_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "sport_id", referencedColumnName = "id")})
    private Set<SportEntity> sports = new HashSet<>();
}