package com.example.fee.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fee Service API")
                        .version("1.0"))
                .servers(List.of(
                        new Server()
                                .url("https://expert-computing-machine-4jp57r9qv7gj2j4g-8081.app.github.dev")
                ));
    }
}
