package com.findapartner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.findapartner")
public class PublicWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicWebApplication.class, args);
	}
}
