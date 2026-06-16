package com.rupesh.ticket_management;

import org.modelmapper.ModelMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TicketManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementApplication.class, args);
	}

	@Bean
	public ModelMapper setMapper() {
		return new ModelMapper();

	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	 @Bean
	    public OpenAPI customOpenAPI() {
	        return new OpenAPI()
	            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
	            .components(new Components()
	                .addSecuritySchemes("Bearer Authentication", new SecurityScheme()
	                    .type(SecurityScheme.Type.HTTP)
	                    .scheme("bearer")
	                    .bearerFormat("JWT")));
	    }

}
