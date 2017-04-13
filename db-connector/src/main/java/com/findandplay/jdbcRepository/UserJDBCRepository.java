package com.findandplay.jdbcRepository;

import com.findandplay.Query;
import com.findandplay.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.stream.IntStream;

@Repository
public class UserJDBCRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(@Qualifier("jdbcDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserWithCreatedAndCheckedAdverts(String msisdn) {
//        UserDTO userDTO = jdbcTemplate.queryForObject(Query.test,
//                (rs, rowNum) -> {
//                    return new UserDTO();
//                }, msisdn);
        UserDTO userDTO = jdbcTemplate.queryForObject(Query.test,
                (rs, rowNum) -> {
                    IntStream.range(0, rs.getMetaData().getColumnCount())
                            .forEach(i -> {
                                try {
                                    System.out.println(rs.getMetaData().getColumnName(i));
                                    System.out.println(rs.getObject(i));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });
                    return new UserDTO();
                }, msisdn);
        return userDTO;
    }
}
