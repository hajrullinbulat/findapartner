package com.findapartner.repository;

import com.findapartner.entity.Person;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long>, QueryDslPredicateExecutor<Person> {
    List<Person> findByLastName(@Param("name") String name);


}
