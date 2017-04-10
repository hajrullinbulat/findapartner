package com.findandplay.entity;

import com.findandplay.enums.AdStatus;
import com.findandplay.enums.Skill;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "adverts")
@Builder

@NamedEntityGraphs(
        @NamedEntityGraph(
                name = "getAdvert",
                attributeNodes = {
                        @NamedAttributeNode("author"),
                        @NamedAttributeNode("sport"),
                        @NamedAttributeNode("users")
                }
        )
)
public class AdvertEntity extends BaseEntity {
    private LocalDateTime created;
    private AdStatus status;
    private Skill level; //min level //todo подумать про max level
    private Integer count;
    private String info;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sport_id")
    private SportEntity sport;

    @OneToMany(
            mappedBy = "advert",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<CheckedAdvertByUserEntity> users;
}