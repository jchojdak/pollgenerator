package com.pollgenerator.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "PollGenerator API",
                version = "1.0",
                description = "Rest API for creating and voting in anonymous polls.",
                contact = @Contact(
                        name = "Jakub Chojdak",
                        email = "jchojdak@gmail.com"
                )
        )
)
public class OpenApiConfig {

}
