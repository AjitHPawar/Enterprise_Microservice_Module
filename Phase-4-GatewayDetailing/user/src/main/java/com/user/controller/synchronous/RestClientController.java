package com.user.controller.synchronous;

import com.user.dto.HttpClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RestClientController {

    private final RestClient accountRestClient;

    @GetMapping("/rest-client")
    public HttpClientResponseDto getLoan() {
        HttpClientResponseDto response = accountRestClient.get()
                .uri("/account/rest-client")
                .retrieve()
                .toEntity(HttpClientResponseDto.class).getBody();

        response.getResponse().put("USER", "Rest Client From User Service");

        return response;
    }
}
