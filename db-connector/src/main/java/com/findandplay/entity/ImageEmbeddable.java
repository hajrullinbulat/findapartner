package com.findandplay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ImageEmbeddable {
    @Column(name = "avatar_low")
    private String low;
    @Column(name = "avatar_medium")
    private String medium;
    @Column(name = "avatar_high")
    private String high;
}
