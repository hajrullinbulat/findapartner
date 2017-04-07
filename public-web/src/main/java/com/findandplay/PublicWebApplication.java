package com.findandplay;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.findandplay")
public class PublicWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicWebApplication.class, args);
    }

    @Bean
    public Module datatypeHibernateModule() {
        return new Hibernate5Module();
    }
}
