package com.card.controller.synchronous;

import com.card.dto.HttpClientResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class RESTTemplateController {
    private final RestTemplate restTemplate;

    @GetMapping("/rest-template")
    @CircuitBreaker(name = "loanService", fallbackMethod = "fallbackLoanServiceRestTemplate")
    public ResponseEntity<HttpClientResponseDto> restTemplate() {
        ResponseEntity<HttpClientResponseDto> forEntity = restTemplate
                .getForEntity("http://localhost:1414/loan/rest-template", HttpClientResponseDto.class);

        forEntity.getBody().getResponse().put("CARD", "From Card Service");

        return forEntity;
    }

    public ResponseEntity<HttpClientResponseDto> fallbackLoanServiceRestTemplate() {
        HttpClientResponseDto response = new HttpClientResponseDto();
        response.getResponse().put("CARD", "Fallback-From Card Service...Loan Service is down");
        return ResponseEntity.ok(response);
    }


}