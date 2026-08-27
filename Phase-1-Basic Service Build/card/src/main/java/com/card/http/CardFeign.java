package com.card.http;

import com.card.dto.HttpClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "loan", url = "http://localhost:1414")
public interface CardFeign {
    @GetMapping("/loan/feign")
    public ResponseEntity<HttpClientResponseDto> getLoan();
}
