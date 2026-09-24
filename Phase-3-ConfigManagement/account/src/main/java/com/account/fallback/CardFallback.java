package com.account.fallback;

import com.account.dto.HttpClientResponseDto;
import com.account.http.AccountFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CardFallback implements FallbackFactory<AccountFeign> {

    @Override
    public AccountFeign create(Throwable cause) {
        log.error("Account fallback triggered", cause);
        return () -> {
            log.warn("Executing fallback logic for account service");
            Map<String, String> response = new HashMap<>();
            response.put("Card-From-Account", "DOWN...!!!");
              HttpClientResponseDto dto = HttpClientResponseDto.builder()
                    .response(response)
                    .build();
            return ResponseEntity.ok(dto);
        };
    }
}