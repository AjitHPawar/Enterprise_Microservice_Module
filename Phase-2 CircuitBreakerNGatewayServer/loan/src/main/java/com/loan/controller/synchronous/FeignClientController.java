package com.loan.controller.synchronous;

import com.loan.dto.HttpClientResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/loan")
public class FeignClientController {

    @GetMapping("/feign")
    public ResponseEntity<HttpClientResponseDto> getLoan() {
        Map<String, String> response = new HashMap<>();
        response.put("LOAN", "Feign Client From Loan Service");

        HttpClientResponseDto responseDto = HttpClientResponseDto.builder()
                .response(response)
                .build();


        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


}
