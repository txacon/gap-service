package com.txacon.gap.infrastructure.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.txacon"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "GAP Proyect",
                "Microservicio de gestión de restauración",
                "VERSION 1.0",
                "https://choosealicense.com/licenses/mit/",
                new Contact("Antonio Díaz-Maroto Chacón", "URL", "antonio.dm.chacon@gmail.com"),
                "MIT License",
                "https://choosealicense.com/licenses/mit/",
                Collections.emptyList()
        );
    }
}
