package com.findandplay.dto;

import com.findandplay.enums.CheckStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckedSectionByUserDTO {
    private Long id;

    private UserDTO user;

    private SectionDTO section;

    private LocalDateTime checked;

    private CheckStatus status;
}