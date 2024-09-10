package com.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{

    @Bean
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce Web Application")
                        .description("A comprehensive e-commerce platform developed using Spring Boot, enabling users to browse products, manage carts, place orders, and handle product management for admins.")
                        .version("3.2.2")
                        .termsOfService("https://www.devrabbit.com/")
                        .contact(new Contact()
                                .name("Mr.Naveen")
                                .email("naveen.gummula@devrabbit.com")
                                .url("http://naveen.linkedIn.com/home"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
