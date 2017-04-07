package com.findandplay.entity;

import com.findandplay.enums.City;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Builder
public class UserEntity extends BaseEntity {
    private String name;
    private String surname;
    private String email;
    private String msisdn;
    private String password;
    private boolean confirmed;
    private String key;
    private LocalDateTime created;
    private LocalDateTime lastAction;
    private City city;
    @Embedded
    private ImageEmbeddable avatar;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<SportOfUserEntity> sports;

    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<AdvertEntity> createdAdverts;

    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<SectionEntity> createdSections;

    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<SpaceEntity> createdSpaces;
}