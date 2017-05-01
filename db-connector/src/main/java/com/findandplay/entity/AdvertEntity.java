package com.findandplay.entity;

import com.findandplay.enums.AdStatus;
import com.findandplay.enums.Skill;
import com.findandplay.enums.SportType;
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
    @Column(name = "advert_created")
    private LocalDateTime created;

    @Column(name = "advert_last_action")
    private LocalDateTime lastActionDate;

    @Column(name = "advert_status")
    @Enumerated(EnumType.STRING)
    private AdStatus status;

    @Column(name = "advert_min_level")
    @Enumerated(EnumType.STRING)
    private Skill minLevel; //min level //todo подумать про max level

    @Column(name = "advert_persons_count")
    private Integer personsCount;

    @Column(name = "advert_info")
    private String info;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_author_id")
    private UserEntity author;

    @Enumerated(EnumType.STRING)
    @Column(name = "advert_sport")
    private SportType sport;

    @OneToMany(
            mappedBy = "advert",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<CheckedAdvertByUserEntity> users;
}