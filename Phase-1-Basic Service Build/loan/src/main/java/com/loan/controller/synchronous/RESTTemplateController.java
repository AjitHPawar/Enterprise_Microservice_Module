package com.loan.controller.synchronous;

import com.loan.dto.HttpClientResponseDto;
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
@RequestMapping("/loan")
@RequiredArgsConstructor
public class RESTTemplateController {
    private final RestTemplate restTemplate;

    @GetMapping("/rest-template")
    public ResponseEntity<HttpClientResponseDto> restTemplate() {
        Map<String, String> response = new HashMap<>();
        response.put("LOAN", "From Loan Service");
        HttpClientResponseDto responseDto = HttpClientResponseDto.builder()
                .response(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
