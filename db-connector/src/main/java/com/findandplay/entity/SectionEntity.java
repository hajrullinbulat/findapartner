package com.findandplay.entity;

import com.findandplay.enums.AdStatus;
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
    private LocalDateTime created;
    private AdStatus status;
    private String address;
    private String info;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sport_id")
    private SportEntity sport;

    @OneToMany(
            mappedBy = "section",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<CheckedSectionByUserEntity> users;
}