package com.user.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 1. Create a load-balanced builder bean
    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    // 2. Create a standard builder for Spring's internal tools (like Eureka)
    @Bean
    @Primary
    public RestClient.Builder directRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public RestClient accountRestClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder restClient) {
        restClient
                .baseUrl("http://account")
                .defaultHeader("Content-Type", "application/json")
                .build();
        return restClient.build();
    }
}
