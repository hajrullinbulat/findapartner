package com.findandplay.dto;

import com.findandplay.enums.Skill;
import com.findandplay.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportOfUserDTO {
    private Long id;
    private UserDTO user;
    private SportType sport;
    private Skill level;
}