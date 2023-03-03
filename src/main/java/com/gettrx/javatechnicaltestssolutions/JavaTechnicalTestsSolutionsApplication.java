package com.gettrx.javatechnicaltestssolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class JavaTechnicalTestsSolutionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTechnicalTestsSolutionsApplication.class, args);
	}

}
