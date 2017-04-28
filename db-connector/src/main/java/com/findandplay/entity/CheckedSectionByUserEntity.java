package com.findandplay.entity;

import com.findandplay.enums.CheckStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "checked_sections")
public class CheckedSectionByUserEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id")
    private SectionEntity section;

    @Column(name = "checked_sections_date")
    private LocalDateTime checked;

    @Column(name = "checked_sections_status")
    @Enumerated(EnumType.STRING)
    private CheckStatus status;
}