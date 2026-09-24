package com.card;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CardApplication implements CommandLineRunner {

    @Value("${message}")
    private String msg;   // instance field, not static

    @Override
    public void run(String... args) {
        System.out.println("Card-Service : " + msg);
    }
	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}

}
