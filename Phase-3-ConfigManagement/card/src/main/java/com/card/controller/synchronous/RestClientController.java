package com.card.controller.synchronous;

import com.card.dto.HttpClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class RestClientController {

    private final RestClient loanRestClient;

    @GetMapping("/rest-client")
    public HttpClientResponseDto getLoan() {
        HttpClientResponseDto response = loanRestClient.get()
                .uri("/loan/rest-client")
                .retrieve()
                .toEntity(HttpClientResponseDto.class).getBody();

        response.getResponse().put("CARD", "Rest Client From Card Service");
/*

        Map<String, String> cardResponse = new HashMap<>();
        cardResponse.put("CARD", "Rest Client From Card Service");
        HttpClientResponseDto responseDto = HttpClientResponseDto.builder()
                .response(cardResponse)
                .build();
        if (response.getBody() != null)
            response.getBody().getResponse().putAll(cardResponse);
*/

        return response;
    }
}

