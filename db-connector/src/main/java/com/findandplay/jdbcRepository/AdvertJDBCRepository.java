package com.findandplay.jdbcRepository;

import com.findandplay.Queries.AdvertQuery;
import com.findandplay.dto.AdvertDTO;
import com.findandplay.enums.SportType;
import com.findandplay.resultSetExtractor.SearchAdvertsWithPagingExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hajrullinbulat on 30.04.17.
 */
@Repository
@Transactional
public class AdvertJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdvertJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public List<AdvertDTO> getAdverts(SportType sportType, Integer limit, Integer offset) {
        return jdbcTemplate.query(
                AdvertQuery.searchAdvertsWithPaging,
                new SearchAdvertsWithPagingExtractor(),
                sportType.name(), limit, offset);
    }
}
