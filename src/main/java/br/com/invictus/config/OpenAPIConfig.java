package br.com.invictus.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RestFul API with Java 22 and Spring Boot 3")
                        .version("v1")
                        .description("Breve descrição da API")
                        .termsOfService("http://localhost:8080/v3/api-docs")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("http://localhost:8080/swagger-ui/index.html")
                        )
                );
    }

}
