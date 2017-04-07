package com.findandplay.entity;

import com.findandplay.enums.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "roles")
public class RoleEntity extends BaseEntity {
    private RoleTypes name;
}