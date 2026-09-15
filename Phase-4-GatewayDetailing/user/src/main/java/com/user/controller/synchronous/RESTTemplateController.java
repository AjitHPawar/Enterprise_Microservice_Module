package com.user.controller.synchronous;

import com.user.dto.HttpClientResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RESTTemplateController {
    private final RestTemplate restTemplate;

    @GetMapping("/rest-template")
    @Retry(name = "accountService", fallbackMethod = "fallbackAccountServiceRestTemplate")
    @CircuitBreaker(name = "accountService", fallbackMethod = "fallbackAccountServiceRestTemplate")
    public ResponseEntity<HttpClientResponseDto> restTemplate() {
        ResponseEntity<HttpClientResponseDto> forEntity = restTemplate
                .getForEntity("http://localhost:1111/account/rest-template", HttpClientResponseDto.class);

        forEntity.getBody().getResponse().put("USER", "From User Service");

        return forEntity;
    }

    public ResponseEntity<HttpClientResponseDto> fallbackAccountServiceRestTemplate(Throwable t) {
        Map<String, String> response = new HashMap<>();
        response.put("USER", t.getMessage());
        HttpClientResponseDto responseDto = HttpClientResponseDto.builder()
                .response(response)
                .build();
        return ResponseEntity.ok(responseDto);
    }
}
