package com.creditcard.app.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages="com.creditcard.app")
public class ApplicationSwaggerConfig {
	
	@Bean
	   public Docket customDocket(){
	      return new Docket(DocumentationType.SWAGGER_2)
	    		  .select()
	              .apis(RequestHandlerSelectors.any())
	              .paths(PathSelectors.ant("/**/"))
	              .build()
	              .apiInfo(getApiInfo());
	   }

	   private ApiInfo getApiInfo() {
		   return new ApiInfo(
			"REST Credit Card backend Api Documentation",
			"This is a REST API documentation for credit card managment system.",
			"1.0",
			"Free to use",
			new Contact(
					"Rucha Sanap",
					"https://github.com/rucha-13",
					"sanaprucha@gmail.com"),
			"API Licence",
			"http://localhost:8090/", Collections.emptyList());
	   }



}
