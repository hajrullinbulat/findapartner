package com.findandplay.entity;

import com.findandplay.enums.AdStatus;
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
public class Section extends BaseEntity {
    private LocalDateTime created;
    private AdStatus status;
    private String info;
    private String address;
}