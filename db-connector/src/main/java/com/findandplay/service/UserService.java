package com.findandplay.service;

import com.findandplay.jpaRepository.AdvertRepository;
import com.findandplay.jpaRepository.CheckedAdverts;
import com.findandplay.jpaRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;
    private final CheckedAdverts checkedAdvertsRepository;

    @Autowired
    public UserService(UserRepository userRepository, AdvertRepository advertRepository, CheckedAdverts checkedAdvertsRepository) {
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
        this.checkedAdvertsRepository = checkedAdvertsRepository;
    }

//    public UserDTO getUserByMsisdnWithCreated(String msisdn) {
//        checkedAdvertsRepository.save()
//        UserEntity user = userRepository.findByMsisdn(msisdn);
//        List<AdvertEntity> advertsByAuthor = advertRepository.findAdvertsByAuthor(user, EntityGraphUtils.fromName("Advert.sport"));
//        checkedAdvertsRepository.findOne()
//
//        advertsByAuthor.stream().map(advertEntity -> {
//            return AdvertDTO.builder().count(advertEntity.getCount())
//                    .sport(advertEntity.getSport().getName())
//                    .
//        })
//
//        UserDTO.builder()
//                .msisdn(user.getMsisdn())
//                .createdAdverts()
//    }
}
