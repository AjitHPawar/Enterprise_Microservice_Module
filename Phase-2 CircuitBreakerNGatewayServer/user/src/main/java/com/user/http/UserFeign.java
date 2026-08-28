package com.user.http;

import com.user.dto.HttpClientResponseDto;
import com.user.fallback.AccountFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account", url = "http://localhost:1414", fallbackFactory = AccountFallback.class)
public interface UserFeign {
    @GetMapping("/account/feign")
    public ResponseEntity<HttpClientResponseDto> getAccount();
}
