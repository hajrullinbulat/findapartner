package com.findandplay.entity;

import com.findandplay.enums.SportType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "sports")
@Builder
public class SportEntity extends BaseEntity {
    @Column(name = "sport_name")
    @Enumerated(EnumType.STRING)
    private SportType name;
}