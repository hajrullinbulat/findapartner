package com.findandplay.resultSetExtractor;

import com.findandplay.dto.*;
import com.findandplay.enums.CheckStatus;
import com.findandplay.enums.SportType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserWithCreatedAndCheckedSectionsExtractor implements ResultSetExtractor<UserDTO> {
    private Map<Long, AdvertDTO> createdAdverts;
    private Map<Long, CheckedAdvertByUserDTO> checkedMyAdverts;
    private Map<Long, CheckedSectionByUserDTO> checkedSections;
    private UserDTO user;

    @Override
    public UserDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (rs.next()) {
            user = Optional.ofNullable(user)
                    .orElse(
                            UserDTO.builder()
                                    .id(rs.getObject("me_id", Long.class))
                                    .name(rs.getString("my_name")).build()
                    );
            Long advertId = rs.getObject("advert_id", Long.class);
            if (advertId != null) {
                createdAdverts = Optional.ofNullable(createdAdverts).orElse(new HashMap<>());
                if (!createdAdverts.containsKey(advertId)) {
                    AdvertDTO advert = AdvertDTO.builder()
                            .id(advertId)
                            .info(rs.getString("advert_info"))
                            .sport(SportType.valueOf(rs.getString("sport_name"))).build();
                    createdAdverts.put(advertId, advert);
                }

                Long checkedAdvertsId = rs.getObject("my_adverts_id", Long.class);
                if (checkedAdvertsId != null) {
                    checkedMyAdverts = Optional.ofNullable(checkedMyAdverts).orElse(Collections.emptyMap());
                    if (!checkedMyAdverts.containsKey(checkedAdvertsId)) {
                        UserDTO checkedAdvertUser = UserDTO.builder()
                                .id(rs.getObject("user_id_from_my_adverts", Long.class))
                                .name(rs.getString("user_name_from_my_adverts")).build();
                        CheckedAdvertByUserDTO checkedAdvert = CheckedAdvertByUserDTO.builder()
                                .id(checkedAdvertsId)
                                .user(checkedAdvertUser)
                                .status(CheckStatus.valueOf(rs.getString("status_of_checked_advert"))).build();
                        checkedMyAdverts.put(checkedAdvertsId, checkedAdvert);

                        AdvertDTO advertDTO = createdAdverts.get(advertId);
                        List<CheckedAdvertByUserDTO> checkedUsers = Optional.ofNullable(advertDTO.getUsers()).orElse(Collections.emptyList());
                        checkedUsers.add(checkedAdvert);
                        advertDTO.setUsers(checkedUsers);
                    }
                }
            }
            Long sectionId = rs.getObject("checked_sections_id", Long.class);
            if (sectionId != null) {
                checkedSections = Optional.ofNullable(checkedSections).orElse(Collections.emptyMap());
                if (!checkedSections.containsKey(sectionId)) {
                    SectionDTO section = SectionDTO.builder()
                            .id(rs.getObject("checked_sections_section_id", Long.class))
                            .info(rs.getString("checked_sections_section_info")).build();
                    UserDTO user = UserDTO.builder()
                            .id(rs.getObject("checked_sections_user_id", Long.class))
                            .name(rs.getString("checked_sections_user_name")).build();
                    CheckedSectionByUserDTO checkedSection = CheckedSectionByUserDTO.builder()
                            .id(sectionId)
                            .section(section)
                            .user(user).build();
                    checkedSections.put(sectionId, checkedSection);
                }
            }
        } else {
            if (user != null) {
                user.setCreatedAdverts(new ArrayList<>(createdAdverts.values()));
                user.setCheckedSetions(new ArrayList<>(checkedSections.values()));
            }
        }
        return user;
    }

}
