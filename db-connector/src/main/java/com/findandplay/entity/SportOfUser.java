package com.findandplay.entity;

import com.findandplay.enums.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SportOfUser extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, )
    private User user;
    private Skill level;
}