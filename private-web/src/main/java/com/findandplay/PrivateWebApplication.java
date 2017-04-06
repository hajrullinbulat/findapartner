package com.findandplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.findandplay")
//@EnableAdminServer
public class PrivateWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivateWebApplication.class, args);
    }
}
