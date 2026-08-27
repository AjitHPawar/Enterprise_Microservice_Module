package com.card.controller.synchronous;

import com.card.dto.HttpClientResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class RESTTemplateController {
    private final RestTemplate restTemplate;

    @GetMapping("/rest-template")
    @Retry(name = "loanService", fallbackMethod = "fallbackLoanServiceRestTemplate")
    @CircuitBreaker(name = "loanService", fallbackMethod = "fallbackLoanServiceRestTemplate")
    public ResponseEntity<HttpClientResponseDto> restTemplate() {
        System.out.println("Request Factory in use: " + restTemplate.getRequestFactory().getClass().getName());
        ResponseEntity<HttpClientResponseDto> forEntity = restTemplate
                .getForEntity("http://localhost:1313/loan/rest-template", HttpClientResponseDto.class);

        forEntity.getBody().getResponse().put("CARD", "From Card Service");

        return forEntity;
    }

    public ResponseEntity<HttpClientResponseDto> fallbackLoanServiceRestTemplate(Throwable t) {
        Map<String, String> response = new HashMap<>();
        response.put("CARD",t.getMessage());
        HttpClientResponseDto responseDto = HttpClientResponseDto.builder()
                .response(response)
                .build();
        return ResponseEntity.ok(responseDto);
    }


}