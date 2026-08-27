package com.card.controller.asynchronous;

import com.card.dto.HttpClientResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@Slf4j
public class WebClientController {
    private final WebClient loanWebClient;

    @GetMapping("/web-client")
    public Mono<HttpClientResponseDto> getLoan() {
        return loanWebClient.get()
                .uri("/loan/web-client")
                .retrieve()
                .bodyToMono(HttpClientResponseDto.class)
                .map(result -> {
                    Map<String, String> map = result.getResponse();
                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put("CARD", "Async-WebClient From Card Service");
                    HttpClientResponseDto responseDto = new HttpClientResponseDto();
                    responseDto.setResponse(map);
                    return responseDto;
                });
    }
}
