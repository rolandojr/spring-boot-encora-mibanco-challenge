package com.example.mibanco.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ApplicationProperties {

    @Value("${spring.application.apiKey}")
    private String apiKey;

    @Value("${spring.application.name}")
    private String component;
}
