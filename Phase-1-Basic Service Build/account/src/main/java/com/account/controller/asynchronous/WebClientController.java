package com.account.controller.asynchronous;

import com.account.dto.HttpClientResponseDto;
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
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class WebClientController {
    private final WebClient cardWebClient;

    @GetMapping("/web-client")
    public Mono<HttpClientResponseDto> getCard() {
        return cardWebClient.get()
                .uri("/card/web-client")
                .retrieve()
                .bodyToMono(HttpClientResponseDto.class)
                .map(result -> {
                    Map<String, String> map = result.getResponse();
                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put("ACCOUNT", "Async-WebClient From Account Service");
                    HttpClientResponseDto responseDto = new HttpClientResponseDto();
                    responseDto.setResponse(map);
                    return responseDto;
                });
    }
}
