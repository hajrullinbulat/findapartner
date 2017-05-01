package com.findandplay.entity;

import com.findandplay.enums.AdStatus;
import com.findandplay.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "sections")
public class SectionEntity extends BaseEntity {
    @Column(name = "section_created")
    private LocalDateTime created;

    @Column(name = "section_last_action")
    private LocalDateTime lastActionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "section_status")
    private AdStatus status;

    @Column(name = "section_address")
    private String address;

    @Column(name = "section_info")
    private String info;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_author_id")
    private UserEntity author;

    @Enumerated(EnumType.STRING)
    @Column(name = "section_sport")
    private SportType sport;

    @OneToMany(
            mappedBy = "section",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<CheckedSectionByUserEntity> users;
}