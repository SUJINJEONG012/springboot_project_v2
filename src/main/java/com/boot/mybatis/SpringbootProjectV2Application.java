package com.boot.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
public class SpringbootProjectV2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProjectV2Application.class, args);
	}
	
	
}
