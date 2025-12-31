package com.octal.votehub.api.v1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class SpringDocConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("VOTEHUB-API")
                        .description("API REST para criação de votação e enquetes.")
                        .version("v1")
                        .license(new License().name("AGPL-3.0").url("https://www.gnu.org/licenses/agpl-3.0.html"))
                        .contact(new Contact().url("https://www.linkedin.com/in/catharynaas"))
        );
    }
}
