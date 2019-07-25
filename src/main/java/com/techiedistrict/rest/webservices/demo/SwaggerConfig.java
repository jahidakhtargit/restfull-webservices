package com.techiedistrict.rest.webservices.demo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT = new Contact("Jahid Akhtar", 
			"https://techiedistrict.blogspot.com/", "jahid.akhtar.git@gmail.com");
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Book Application APIs", 
			"Api Documentation for Book Application", "1.0", "urn:tos",
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", 
			new ArrayList<VendorExtension>());
	private static Set<String> producesAndConsumes = new HashSet<>(Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(producesAndConsumes)
				.consumes(producesAndConsumes)
				.select()                                  
				.apis(RequestHandlerSelectors.any())              
				.paths(PathSelectors.any())

				.build();	
	}


}
