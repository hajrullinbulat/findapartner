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
public class CheckedAdvertByUserDTO {
    private Long id;

    private UserDTO user;

    private AdvertDTO advert;

    private LocalDateTime checked;

    private CheckStatus status;
}
