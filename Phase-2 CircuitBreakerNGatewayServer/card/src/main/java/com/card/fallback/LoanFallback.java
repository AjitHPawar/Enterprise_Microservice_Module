package com.card.fallback;

import com.card.dto.HttpClientResponseDto;
import com.card.http.CardFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LoanFallback implements FallbackFactory<CardFeign> {

    @Override
    public CardFeign create(Throwable cause) {
        log.error("Card fallback triggered", cause);

        return new CardFeign() {
            @Override
            public ResponseEntity<HttpClientResponseDto> getLoan() {
                log.warn("Executing fallback logic for card service");

                Map<String, String> response = new HashMap<>();
                response.put("LOAN", "DOWN...!!!");
                HttpClientResponseDto dto = HttpClientResponseDto.builder()
                        .response(response)
                        .build();

                return ResponseEntity.ok(dto);
            }
        };
    }
}
