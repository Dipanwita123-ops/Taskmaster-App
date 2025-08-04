package com.bootangularpjct.rest.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})

@ComponentScan(basePackages = {
	    "com.bootangularpjct.rest.webservices"
	})


public class BootangularrestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootangularrestapiApplication.class, args);
	}

}
