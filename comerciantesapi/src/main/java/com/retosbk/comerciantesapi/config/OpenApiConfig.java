package com.retosbk.comerciantesapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Comerciantes API",
                version = "1.0",
                description = "Documentación de la API para comerciantes",
                termsOfService = "Jose Fernando Berna josefbernam@gmail.com"
        ),
        tags = {
                @Tag(name = "Autenticación", description = "Login y generación de token JWT"),
                @Tag(name = "Municipios", description = "Gestión de municipios"),
                @Tag(name = "Comerciantes", description = "Gestión de comerciantes"),
                @Tag(name = "Usuarios", description = "Gestión de usuarios del sistema"),
                @Tag(name = "Establecimientos", description = "Gestión de establecimientos")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}
