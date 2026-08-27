package com.user.http;

import com.user.dto.HttpClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account", url = "http://localhost:1414")
public interface UserFeign {
    @GetMapping("/account/feign")
    public ResponseEntity<HttpClientResponseDto> getAccount();
}
