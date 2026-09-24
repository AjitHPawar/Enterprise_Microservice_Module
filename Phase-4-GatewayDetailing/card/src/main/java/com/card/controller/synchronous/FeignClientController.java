package com.card.controller.synchronous;

import com.card.dto.HttpClientResponseDto;
import com.card.http.CardFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class FeignClientController {
    @Autowired
    private CardFeign cardFeign;

    @GetMapping("/feign")
    public ResponseEntity<HttpClientResponseDto> getCard() {
        ResponseEntity<HttpClientResponseDto> response = cardFeign.getLoan();
        response.getBody().getResponse().put("CARD", "Feign Client From Card Service");
        return response;
    }

}
