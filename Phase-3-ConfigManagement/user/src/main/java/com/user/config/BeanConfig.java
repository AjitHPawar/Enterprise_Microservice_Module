package com.user.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate() {
        java.net.http.HttpClient httpClient = java.net.http.HttpClient.newHttpClient();
        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
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

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient accountWebClient(WebClient.Builder loadBalancedWebClientBuilder) {
        return loadBalancedWebClientBuilder
                .baseUrl("lb://GATEWAYSERVER")   // must match spring.application.name of gateway, case-insensitive
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
