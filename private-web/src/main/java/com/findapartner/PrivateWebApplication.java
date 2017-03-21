package com.findapartner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.findapartner")
//@EnableAdminServer
public class PrivateWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivateWebApplication.class, args);
    }
}
