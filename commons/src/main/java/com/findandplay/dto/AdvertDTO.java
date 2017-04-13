package com.findandplay.dto;

import com.findandplay.enums.AdStatus;
import com.findandplay.enums.Skill;
import com.findandplay.enums.SportType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertDTO {
    private Long id;
    private LocalDateTime created;
    private AdStatus status;
    private Skill level; //min level //todo подумать про max level
    private Integer count;
    private String info;

    private UserDTO author;

    private SportType sport;

    private List<CheckedAdvertByUserDTO> users;
}