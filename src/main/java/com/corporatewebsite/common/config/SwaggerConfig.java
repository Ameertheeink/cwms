package com.corporatewebsite.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI corporateWebsiteOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Corporate Website Management System API")
                                .description("REST APIs for Corporate Website Management System")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Ameertheein")
                                                .email("admin@cwms.com")
                                )
                );
    }
}