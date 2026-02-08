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
                                .url("https://upgraded-space-barnacle-97gr9x465976f7wrx-8081.app.github.dev")
                ));
    }
}
