package com.ApiLibreria;

import com.ApiLibreria.DTO.RespuestaApiDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class ApiLibreriaApplication extends SpringBootServletInitializer {




    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiLibreriaApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiLibreriaApplication.class, args);
    }

}
