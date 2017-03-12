package com.findapartner.controllers;

import findapartner.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by hajrullinbulat on 11.03.17.
 */
@Controller
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/")
    public String testGet(){
        return testService.getField();
    }
}
