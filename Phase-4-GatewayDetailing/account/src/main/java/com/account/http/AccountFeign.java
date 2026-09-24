package com.account.http;

import com.account.dto.HttpClientResponseDto;
import com.account.fallback.CardFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Fallback;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "card", url = "http://localhost:1414", fallbackFactory = CardFallback.class)
public interface AccountFeign {
    @GetMapping("/card/feign")
    public ResponseEntity<HttpClientResponseDto> getCard();
}
