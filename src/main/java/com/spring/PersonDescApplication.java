package com.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableAdminServer
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.spring.dao")
public class PersonDescApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonDescApplication.class, args);
	}

}
