package com.example.mibanco.configuration;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class BulkheadConfiguration {

    @Bean
    public Bulkhead bulkheadCustomConfiguration() {
        // Create a custom configuration for a Bulkhead
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(150)
                .maxWaitDuration(Duration.ofMillis(500))
                .build();

        // Create a BulkheadRegistry with a custom global configuration
        BulkheadRegistry registry = BulkheadRegistry.of(config);

        // Get or create a Bulkhead from the registry -
        // bulkhead will be backed by the default config
        Bulkhead bulkheadWithDefaultConfig = registry.bulkhead("exchange");
        return bulkheadWithDefaultConfig;

    }
}
