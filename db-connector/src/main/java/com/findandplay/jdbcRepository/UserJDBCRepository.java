package com.findandplay.jdbcRepository;

import com.findandplay.Query;
import com.findandplay.dto.UserDTO;
import com.findandplay.resultSetExtractor.UserWithCreatedAndCheckedSectionsExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
public class UserJDBCRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(@Qualifier("jdbcDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserWithCreatedAndCheckedSections(String msisdn) {
        return jdbcTemplate.query(Query.test, new UserWithCreatedAndCheckedSectionsExtractor(), msisdn);
    }
}
