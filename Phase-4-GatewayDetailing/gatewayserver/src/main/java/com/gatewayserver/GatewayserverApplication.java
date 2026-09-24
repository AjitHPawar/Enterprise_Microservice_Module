package com.gatewayserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class GatewayserverApplication implements CommandLineRunner {
    @Value("${message}")
    private String msg;   // instance field, not static

    @Override
    public void run(String... args) {
        System.out.println("GateWay-Service : " + msg);
    }

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

}
