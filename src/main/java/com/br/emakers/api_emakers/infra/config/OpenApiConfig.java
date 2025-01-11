package com.br.emakers.api_emakers.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto da Emakers") //titulo da documentacao
                        .version("v23") //versao da documentacao
                        .description("Uma API feita durante o processo seletivo da emakers") //descricao da documentacao
                )
                .servers(Arrays.asList(
                        new Server().url("http://localhost:8080").description("Servidor local")
                ));
    }
}
