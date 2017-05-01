package com.findandplay.service;

import com.findandplay.dto.AdvertDTO;
import com.findandplay.enums.SportType;
import com.findandplay.jdbcRepository.AdvertJDBCRepository;
import com.findandplay.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hajrullinbulat on 30.04.17.
 */
@Service
public class AdvertJDBCService {
    private final AdvertJDBCRepository advertRepository;
    private final Integer paginationLimit;

    @Autowired
    public AdvertJDBCService(
            AdvertJDBCRepository advertRepository,
            @Value("${pagination.adverts.limit}") Integer paginationLimit
    ) {
        this.advertRepository = advertRepository;
        this.paginationLimit = paginationLimit;
    }

    public List<AdvertDTO> getAdverts(SportType sportType, Integer page) {
        return advertRepository.getAdverts(sportType, paginationLimit, PaginationUtil.getOffset(paginationLimit, page));
    }


}
