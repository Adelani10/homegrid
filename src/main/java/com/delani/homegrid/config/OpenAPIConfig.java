package com.delani.homegrid.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "HomeGrid API",
                description = "API documentation for HomeGrid App",
                version = "1.0",
                contact = @Contact(
                        name = "Ade Lani",
                        email = "harounadelani@gmail.com"
                )
        ),
        servers = {
                @Server(
                description = "Local ENV",
                url = "http://localhost:8082/api/v1"
                ),
                @Server(
                        description = "Render Production ENV",
                        url = "https://homegrid-api.onrender.com/api/v1"
                )
        },
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication using Bearer token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfig {
}
