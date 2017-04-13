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
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Advert.author_sport",
                attributeNodes = {
                        @NamedAttributeNode("author"),
                        @NamedAttributeNode("sport")
                }
        ),
        @NamedEntityGraph(
                name = "Advert.sport",
                attributeNodes = {
                        @NamedAttributeNode("sport")
                }
        )
})
public class AdvertEntity extends BaseEntity {
    private LocalDateTime created;
    @Enumerated(EnumType.STRING)
    private AdStatus status;
    @Enumerated(EnumType.STRING)
    private Skill level; //min level //todo подумать про max level
    private Integer count;
    private String info;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
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