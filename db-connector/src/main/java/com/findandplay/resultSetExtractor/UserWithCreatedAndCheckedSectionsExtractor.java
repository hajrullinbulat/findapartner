package com.findandplay.resultSetExtractor;

import com.findandplay.dto.AdvertDTO;
import com.findandplay.dto.CheckedSectionByUserDTO;
import com.findandplay.dto.UserDTO;
import com.findandplay.enums.SportType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserWithCreatedAndCheckedSectionsExtractor implements ResultSetExtractor<UserDTO> {
    private Map<Long, AdvertDTO> createdAdverts;
    private Map<Long, UserDTO> usersThatCheckedCreatedAdverts;
    private Map<Long, CheckedSectionByUserDTO> checkedSections;
    private Map<Long, UserDTO> usersThatAlsoCheckedSections;
    private UserDTO user;

    @Override
    public UserDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
        user = new UserDTO();
        if (rs.next()) {
            user.setId(rs.getObject("me_id", Long.class));
            user.setName(rs.getString("my_name"));

            Long advertId = rs.getObject("advert_id", Long.class);
            if (advertId != null) {
                createdAdverts = Optional.ofNullable(createdAdverts).orElse(new HashMap<>());
                if (!createdAdverts.containsKey(advertId)) {
                    AdvertDTO.builder()
                            .id(advertId)
                            .info(rs.getString("advert_info"))
                            .sport(SportType.valueOf())
                }
            }
        }
    }

}
