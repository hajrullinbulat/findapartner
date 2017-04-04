package com.findapartner.controller;

import com.findapartner.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hajrullinbulat on 11.03.17.
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    private final PersonRepository personRepository;

    @GetMapping("/jpa")
    @ResponseBody
    public ResponseEntity jpaGet() {
        return ResponseEntity.badRequest().body(personRepository.findAll());
    }

    @GetMapping("/dsl")
    @ResponseBody
    public ResponseEntity queryDslGet() {
        return ResponseEntity.badRequest().body(personRepository.findAll());
    }
}
