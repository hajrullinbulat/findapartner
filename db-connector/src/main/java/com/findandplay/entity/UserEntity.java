package com.findandplay.entity;

import com.findandplay.configuration.UserSportsJsonType;
import com.findandplay.enums.City;
import com.findandplay.json.UserSportsJson;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@TypeDef(name = "sports", typeClass = UserSportsJsonType.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "UserEntity.roles", attributeNodes = {
                @NamedAttributeNode("roles"),
        })
})
public class UserEntity extends BaseEntity {
    @Column(name = "user_name")
    private String name;

    @Column(name = "user_surname")
    private String surname;

    @Column(name = "user_email")
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

    @Column(name = "user_last_password_reset")
    private Date lastPasswordReset;

    @Column(name = "user_sports")
    @Type(type = "sports")
    private UserSportsJson sports;

    @Enumerated(EnumType.STRING)
    private City city;

    @Embedded
    private ImageEmbeddable avatar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<RoleEntity> roles = new HashSet<>();

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