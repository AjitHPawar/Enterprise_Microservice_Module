package com.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder route = builder.routes()
                .route("account", r -> r.path("/account/**").uri("http://localhost:1111"))
                .route("card", r -> r.path("/card/**").uri("http://localhost:1212"))
                .route("loan", r -> r.path("/loan/**").uri("http://localhost:1313"))
                .route("user", r -> r.path("/user/**").uri("http://localhost:1515"));
        return route.build();
    }

}
