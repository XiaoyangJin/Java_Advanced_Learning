package com.assignment.RestAPI_CompletableFuture.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
