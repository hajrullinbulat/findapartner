package com.findandplay.entity;

import com.findandplay.configuration.UserSportsJsonType;
import com.findandplay.enums.AdStatus;
import com.findandplay.json.SpaceSportsJson;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@TypeDef(name = "sports", typeClass = SpaceSportsJson.class)
@Table(name = "spaces")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

    @Type(type = "sports")
    private SpaceSportsJson sports;
}