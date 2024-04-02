package com.vaishnavi.blog.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Collections;
import java.util.List;


@Configuration
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER ="Authorization";
	
	private ApiKey apiKeys() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	private List<SecurityContext> securityContexts(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sr()).build());
	}
	
	private List<SecurityReference> sr(){
		AuthorizationScope scope = new  AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getinfo()).securityContexts(securityContexts()).securitySchemes(Arrays.asList(apiKeys())).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	private ApiInfo getinfo() {
		
		return new ApiInfo("Blogging Application: Backend Course", "This project is developed by Vaishnavi Salaskar", "1.0", "Terms of Service", new Contact("Vaishnavi", "https://www.github.com/vsala2", "vaishnavisalaskar0@gmail.com"), "License of APIs", "Api license URL", Collections.emptyList());
	}

}
