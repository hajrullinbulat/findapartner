package com.findandplay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO implements Serializable{
    private String low;
    private String medium;
    private String high;

    public static ImageDTO empty() {
        return ImageDTO.builder().build();
    }
}
