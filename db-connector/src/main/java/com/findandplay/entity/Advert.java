package com.findandplay.entity;

import com.findandplay.enums.AdStatus;
import com.findandplay.enums.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Advert extends BaseEntity {
    private LocalDateTime created;
    private AdStatus status;
    private Skill level; //min level //todo подумать про max level
    private Integer count;
    private String info;
}