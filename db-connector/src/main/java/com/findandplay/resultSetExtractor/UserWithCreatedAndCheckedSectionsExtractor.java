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
    private Map<Long, SectionDTO> checkedSections;
    private Map<Long, CheckedSectionByUserDTO> checkedSectionsByUsers;
    private UserDTO user;


    @Override
    public UserDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
        while (rs.next()) {
            user = Optional.ofNullable(user)
                    .orElse(
                            UserDTO.builder()
                                    .id((Long) rs.getObject("me_id"))
                                    .name(rs.getString("my_name")).build()
                    );
            Long advertId = (Long) rs.getObject("advert_id");
            if (advertId != null) {
                createdAdverts = Optional.ofNullable(createdAdverts).orElse(new HashMap<>());
                if (!createdAdverts.containsKey(advertId)) {
                    AdvertDTO advert = AdvertDTO.builder()
                            .id(advertId)
                            .info(rs.getString("advert_info"))
                            .sport(SportType.valueOf(rs.getString("advert_sport_name"))).build();
                    createdAdverts.put(advertId, advert);
                }
                Long checkedAdvertsId = (Long) rs.getObject("my_adverts_id");
                if (checkedAdvertsId != null) {
                    checkedMyAdverts = Optional.ofNullable(checkedMyAdverts).orElse(new HashMap<>());
                    if (!checkedMyAdverts.containsKey(checkedAdvertsId)) {
                        UserDTO checkedAdvertUser = UserDTO.builder()
                                .id((Long) rs.getObject("user_id_from_my_adverts"))
                                .name(rs.getString("user_name_from_my_adverts")).build();
                        CheckedAdvertByUserDTO checkedAdvert = CheckedAdvertByUserDTO.builder()
                                .id(checkedAdvertsId)
                                .user(checkedAdvertUser)
                                .status(CheckStatus.valueOf(rs.getString("status_of_checked_advert"))).build();
                        checkedMyAdverts.put(checkedAdvertsId, checkedAdvert);

                        AdvertDTO advertDTO = createdAdverts.get(advertId);
                        List<CheckedAdvertByUserDTO> checkedUsers = Optional.ofNullable(advertDTO.getCheckedUsers()).orElse(new ArrayList<>());
                        checkedUsers.add(checkedAdvert);
                        advertDTO.setCheckedUsers(checkedUsers);
                    }
                }
            }
            Long sectionId = (Long) rs.getObject("me_checked_sections_id");
            if (sectionId != null) {
                checkedSections = Optional.ofNullable(checkedSections).orElse(new HashMap<>());
                if (!checkedSections.containsKey(sectionId)) {
                    SectionDTO section = SectionDTO.builder()
                            .id(sectionId)
                            .info(rs.getString("checked_sections_section_info")).build();
                    checkedSections.put(sectionId, section);
                }
                Long checkedSectionsId = (Long) rs.getObject("checked_sections_id");
                if (checkedSectionsId != null) {
                    checkedSectionsByUsers = Optional.ofNullable(checkedSectionsByUsers).orElse(new HashMap<>());
                    if (!checkedSectionsByUsers.containsKey(checkedSectionsId)) {
                        UserDTO user = UserDTO.builder()
                                .id((Long) rs.getObject("checked_sections_user_id"))
                                .name(rs.getString("checked_sections_user_name")).build();
                        CheckedSectionByUserDTO checkedSection = CheckedSectionByUserDTO.builder()
                                .id(checkedSectionsId)
                                .user(user).build();
                        checkedSectionsByUsers.put(checkedSectionsId, checkedSection);

                        SectionDTO sectionDTO = checkedSections.get(sectionId);
                        List<CheckedSectionByUserDTO> sectionCheckedUsers = Optional.ofNullable(sectionDTO.getCheckedUsers()).orElse(new ArrayList<>());
                        sectionCheckedUsers.add(checkedSection);
                        sectionDTO.setCheckedUsers(sectionCheckedUsers);
                    }
                }
            }
        }
        if (user != null) {
            if (createdAdverts != null) {
                user.setCreatedAdverts(new ArrayList<>(createdAdverts.values()));
            }
            if (checkedSections != null) {
                user.setCheckedSetions(new ArrayList<>(checkedSections.values()));
            }
        }
        return user;
    }

}
