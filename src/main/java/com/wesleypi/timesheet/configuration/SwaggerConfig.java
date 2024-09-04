package com.wesleypi.timesheet.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("API Timesheet")
                        .description("API para controle e lançamento de horas")
                        .version("1.0.0"))
                .addServersItem(new Server()
                        .url("http://localhost:8080/")
                        .description("")
                );
    }

}
