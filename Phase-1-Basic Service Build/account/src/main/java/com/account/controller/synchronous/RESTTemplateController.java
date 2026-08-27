package com.account.controller.synchronous;

import com.account.dto.HttpClientResponseDto;
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
@RequestMapping("/account")
@RequiredArgsConstructor
public class RESTTemplateController {
    private final RestTemplate restTemplate;

    @GetMapping("/rest-template")
    public ResponseEntity<HttpClientResponseDto> restTemplate() {
        ResponseEntity<HttpClientResponseDto> forEntity = restTemplate
                .getForEntity("http://localhost:1414/card/rest-template", HttpClientResponseDto.class);

        forEntity.getBody().getResponse().put("ACCOUNT", "From Account Service");
        return forEntity;
    }

}
