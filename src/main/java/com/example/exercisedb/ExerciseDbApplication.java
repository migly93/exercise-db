package com.example.exercisedb;


import com.example.exercisedb.dto.responses.BadRequestResponse;
import com.example.exercisedb.dto.responses.BaseErrorResponse;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class ExerciseDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExerciseDbApplication.class, args);
    }

    @Bean
    public Docket swagger(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.exercisedb.controllers"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .additionalModels(typeResolver.resolve(BaseErrorResponse.class))
                .additionalModels(typeResolver.resolve(BadRequestResponse.class));
    }

}
