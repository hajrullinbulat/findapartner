package com.findandplay.entity;

import com.findandplay.enums.CheckStatus;
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
public class CheckedSectionByUser extends BaseEntity {

    private LocalDateTime checked;
    private CheckStatus status;
}