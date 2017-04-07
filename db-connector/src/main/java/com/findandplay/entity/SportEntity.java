package com.findandplay.entity;

import com.findandplay.enums.SportType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "sports")
@Builder
public class SportEntity extends BaseEntity {
    private SportType name;
}