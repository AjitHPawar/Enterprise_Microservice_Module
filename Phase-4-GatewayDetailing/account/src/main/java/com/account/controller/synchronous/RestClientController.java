package com.account.controller.synchronous;

import com.account.dto.HttpClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class RestClientController {

    private final RestClient cardRestClient;

    @GetMapping("/rest-client")
    public HttpClientResponseDto getLoan() {
        HttpClientResponseDto response = cardRestClient.get()
                .uri("/card/rest-client")
                .retrieve()
                .toEntity(HttpClientResponseDto.class).getBody();

        response.getResponse().put("ACCOUNT", "Rest Client From Account Service");
        return response;
    }
}

