package com.findandplay.resultSetExtractor;

import com.findandplay.dto.AdvertDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SearchAdvertsWithPagingExtractor implements ResultSetExtractor<List<AdvertDTO>> {

    @Override
    public List<AdvertDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
        //todo extractor
        return null;

    }

}
