package com.findandplay.resultSetExtractor;

import com.findandplay.dto.AdvertDTO;
import com.findandplay.dto.CheckedAdvertByUserDTO;
import com.findandplay.dto.UserDTO;
import com.findandplay.enums.AdStatus;
import com.findandplay.enums.CheckStatus;
import com.findandplay.enums.Skill;
import com.findandplay.enums.SportType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SearchAdvertsWithPagingExtractor implements ResultSetExtractor<List<AdvertDTO>> {
    private Map<Long, AdvertDTO> adverts;
    private Map<Long, CheckedAdvertByUserDTO> checkedAdverts;

    //todo оптимизировать в плане кода
    @Override
    public List<AdvertDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
        while (rs.next()) {
            Long advertId = (Long) rs.getObject("advert_id");
            if (advertId != null) {
                adverts = Optional.ofNullable(adverts).orElse(new HashMap<>());
                if (!adverts.containsKey(advertId)) {
                    AdvertDTO advert = AdvertDTO.builder()
                            .id(advertId)
                            .minLevel(Skill.valueOf(rs.getString("advert_min_level")))
                            .personsCount(rs.getInt("advert_persons_count"))
                            .status(AdStatus.valueOf(rs.getString("advert_status")))
                            .info(rs.getString("advert_info"))
                            .sport(SportType.valueOf(rs.getString("advert_sport")))
                            .author(
                                    UserDTO.builder()
                                            .name(rs.getString("author_name"))
                                            .msisdn(rs.getString("author_msisdn"))
                                            .build()
                            )
                            .build();
                    adverts.put(advertId, advert);
                }
                Long checkedAdvertId = (Long) rs.getObject("checked_advert_id");
                if (checkedAdvertId != null) {
                    checkedAdverts = Optional.ofNullable(checkedAdverts).orElse(new HashMap<>());
                    if (!checkedAdverts.containsKey(checkedAdvertId)) {
                        UserDTO checkedUser = UserDTO.builder()
                                .id((Long) rs.getObject("checked_user_id"))
                                .name(rs.getString("checked_user_name"))
                                .msisdn(rs.getString("checked_user_msisdn"))
                                .build();
                        CheckedAdvertByUserDTO checkedAdvert = CheckedAdvertByUserDTO.builder()
                                .id(checkedAdvertId)
                                .user(checkedUser)
                                .status(CheckStatus.valueOf(rs.getString("checked_advert_status")))
                                .build();
                        checkedAdverts.put(checkedAdvertId, checkedAdvert);

                        AdvertDTO advertDTO = adverts.get(advertId);
                        List<CheckedAdvertByUserDTO> checkedUsers = Optional.ofNullable(advertDTO.getCheckedUsers()).orElse(new ArrayList<>());
                        checkedUsers.add(checkedAdvert);
                        advertDTO.setCheckedUsers(checkedUsers);
                    }
                }
            }
        }
        return new ArrayList<>(adverts.values());
    }
}
