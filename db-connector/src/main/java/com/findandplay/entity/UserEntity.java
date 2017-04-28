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
@NamedEntityGraphs(
        @NamedEntityGraph(
                name = "User.default",
                attributeNodes = {
                        @NamedAttributeNode("roles"),
                }
        )
)
public class UserEntity extends BaseEntity {
    @Column(name = "user_name")
    private String name;

    @Column(name = "user_surname")
    private String surname;

    private String email;

    private String msisdn;

    private String password;

    private boolean confirmed;

    @Column(name = "verification_key")
    private String key;

    @Column(name = "user_created")
    private LocalDateTime created;

    @Column(name = "user_last_action")
    private LocalDateTime lastAction;

    @Enumerated(EnumType.STRING)
    private City city;

    @Embedded
    private ImageEmbeddable avatar;

    @ManyToMany(fetch = FetchType.EAGER)
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