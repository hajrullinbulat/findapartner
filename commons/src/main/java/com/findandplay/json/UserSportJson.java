package com.findandplay.json;

import com.findandplay.enums.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by hajrullinbulat on 30.04.17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSportJson implements Serializable{
    private String name;
    private Skill level;
}
