package com.example.mibanco.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ApplicationProperties {

    @Value("${spring.application.name}")
    private String component;

    @Value("${service.backend.exchange.read-timeout}")
    private Long readTimeout;

    @Value("${service.backend.exchange.write-timeout}")
    private Long writeTimeout;

    @Value("${service.backend.exchange.connect-timeout}")
    private Long connectTimeout;

    @Value("${spring.application.client.exchange.url}")
    private String clientUrl;
}
