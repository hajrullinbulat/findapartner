package com.findandplay.resultSetExtractor;

import com.findandplay.dto.AdvertDTO;
import com.findandplay.dto.CheckedAdvertByUserDTO;
import com.findandplay.dto.UserDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserWithCheckedAdvetsExtractor implements ResultSetExtractor<UserDTO> {
    private Map<Long, AdvertDTO> checkedAdverts;
    private Map<Long, CheckedAdvertByUserDTO> anotherUserThatCheckedAdverts;
    private UserDTO user;

    @Override
    public UserDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
        //todo extractor
        return null;

    }

}
