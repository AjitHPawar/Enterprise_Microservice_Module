package com.user.fallback;

import com.user.dto.HttpClientResponseDto;
import com.user.http.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AccountFallback implements FallbackFactory<UserFeign> {
    @Override
    public UserFeign create(Throwable cause) {
        log.error("User fallback triggered", cause);
        return () -> {
            log.warn("Executing fallback logic for user service");
            Map<String, String> response = new HashMap<>();
            response.put("ACCOUNT-From-User", "DOWN...!!!");
            HttpClientResponseDto dto = HttpClientResponseDto.builder()
                    .response(response)
                    .build();
            return ResponseEntity.ok(dto);
        };
    }

}
