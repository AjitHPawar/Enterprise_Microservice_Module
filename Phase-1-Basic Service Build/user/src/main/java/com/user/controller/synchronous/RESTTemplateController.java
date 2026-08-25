package com.user.controller.synchronous;

import com.user.dto.HttpClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RESTTemplateController {
    private final RestTemplate restTemplate;

    @GetMapping("/rest-template")
    public ResponseEntity<HttpClientResponseDto> restTemplate() {
        ResponseEntity<HttpClientResponseDto> forEntity = restTemplate
                .getForEntity("http://localhost:1414/account/rest-template", HttpClientResponseDto.class);

        forEntity.getBody().getResponse().put("USER", "From User Service");

        return forEntity;
    }

}
