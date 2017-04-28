package com.findandplay.dto;

import com.findandplay.enums.AdStatus;
import com.findandplay.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDTO{
    private Long id;

    private LocalDateTime created;

    private LocalDateTime lastActionDate;

    private AdStatus status;

    private String address;

    private String info;

    private UserDTO author;

    private SportType sport;

    private List<CheckedSectionByUserDTO> checkedUsers;
}