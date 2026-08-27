package com.user.controller.asynchronous;

import com.user.dto.HttpClientResponseDto;
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
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class WebClientController {

    private final WebClient accountWebClient;

    @GetMapping("/web-client")
    public Mono<HttpClientResponseDto> getAccount() {
        return accountWebClient.get()
                .uri("/account/web-client")
                .retrieve()
                .bodyToMono(HttpClientResponseDto.class)
                .map(result -> {
                    Map<String, String> map = result.getResponse();
                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put("USER", "Async-WebClient From User Service");
                    HttpClientResponseDto responseDto = new HttpClientResponseDto();
                    responseDto.setResponse(map);
                    return responseDto;
                });
    }
}
