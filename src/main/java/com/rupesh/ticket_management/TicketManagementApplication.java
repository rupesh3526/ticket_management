package com.rupesh.ticket_management;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@SpringBootApplication
@EnableCaching
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
	 
	 @Bean
	    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
	        RedisTemplate<String, Object> template = new RedisTemplate<>();
	        template.setConnectionFactory(connectionFactory);
	        template.setKeySerializer(new StringRedisSerializer());
	        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
	        return template;
	    }
	 
	 @Bean
	 CorsConfigurationSource corsConfigurationSource() {
	     CorsConfiguration configuration = new CorsConfiguration();

	     configuration.setAllowedOrigins(List.of(
	             "http://localhost:63342"
	     ));

	     configuration.setAllowedMethods(List.of("*"));
	     configuration.setAllowedHeaders(List.of("*"));
	     configuration.setAllowCredentials(true);

	     UrlBasedCorsConfigurationSource source =
	             new UrlBasedCorsConfigurationSource();

	     source.registerCorsConfiguration("/**", configuration);

	     return source;
	 }
}
