package com.findandplay.dto;

import com.findandplay.enums.City;
import com.findandplay.enums.RoleTypes;
import com.findandplay.json.UserSportsJson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String msisdn;

    private String password;

    private boolean confirmed;

    private String key;

    private LocalDateTime created;

    private LocalDateTime lastAction;

    private Date lastPasswordReset;

    private City city;

    private ImageDTO avatar;

    private Set<RoleTypes> roles;

    private UserSportsJson sports;

    private List<AdvertDTO> createdAdverts;

    private List<SectionDTO> createdSections;

    private List<SpaceDTO> createdSpaces;

    private List<AdvertDTO> checkedAdverts;

    private List<SectionDTO> checkedSetions;
}
