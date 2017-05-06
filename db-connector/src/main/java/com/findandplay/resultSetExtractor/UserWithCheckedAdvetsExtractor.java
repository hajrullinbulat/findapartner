package com.findandplay.resultSetExtractor;

import com.findandplay.dto.AdvertDTO;
import com.findandplay.dto.CheckedAdvertByUserDTO;
import com.findandplay.dto.UserDTO;
import com.findandplay.enums.CheckStatus;
import com.findandplay.enums.SportType;
import com.google.common.collect.Lists;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserWithCheckedAdvetsExtractor implements ResultSetExtractor<UserDTO> {
    private Map<Long, AdvertDTO> checkedAdverts;
    private Map<Long, CheckedAdvertByUserDTO> checkedAdvertByUsers;
    private UserDTO user;

    @Override
    public UserDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
        while (rs.next()) {
            user = Optional.ofNullable(user)
                    .orElse(
                            UserDTO.builder()
                                    .id((Long) rs.getObject("me_id"))
                                    .name(rs.getString("me_user_name")).build()
                    );
            Long advertId = (Long) rs.getObject("advert_id");
            if (advertId != null) {
                checkedAdverts = Optional.ofNullable(checkedAdverts).orElse(new HashMap<>());
                if (!checkedAdverts.containsKey(advertId)) {
                    AdvertDTO advert = AdvertDTO.builder()
                            .id(advertId)
                            .info(rs.getString("advert_info"))
                            .sport(SportType.valueOf(rs.getString("advert_sport")))
                            .build();
                    checkedAdverts.put(advertId, advert);
                }
                Long checkedAdvertId = (Long) rs.getObject("checked_adverts_id");
                if (checkedAdvertId != null) {
                    checkedAdvertByUsers = Optional.ofNullable(checkedAdvertByUsers).orElse(new HashMap<>());
                    if (!checkedAdvertByUsers.containsKey(checkedAdvertId)) {
                        UserDTO user = UserDTO.builder()
                                .id((Long) rs.getObject("user_id"))
                                .name(rs.getString("user_name"))
                                .email(rs.getString("user_email"))
                                .build();
                        CheckedAdvertByUserDTO checkedAdvert = CheckedAdvertByUserDTO.builder()
                                .id(checkedAdvertId)
                                .status(CheckStatus.valueOf(rs.getString("checked_adverts_status")))
                                .user(user).build();
                        checkedAdvertByUsers.put(checkedAdvertId, checkedAdvert);

                        AdvertDTO advertDTO = checkedAdverts.get(advertId);
                        List<CheckedAdvertByUserDTO> advertCheckedUsers = Optional.ofNullable(advertDTO.getCheckedUsers()).orElse(new ArrayList<>());
                        advertCheckedUsers.add(checkedAdvert);
                        advertDTO.setCheckedUsers(advertCheckedUsers);
                    }
                }
            }
        }
        if (user != null) {
            Optional.ofNullable(checkedAdverts)
                    .ifPresent(checkedAdverts ->
                            user.setCheckedAdverts(Lists.newArrayList(checkedAdverts.values()))
                    );
        }
        return user;
    }
}