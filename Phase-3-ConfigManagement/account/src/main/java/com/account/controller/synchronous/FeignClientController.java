package com.account.controller.synchronous;


import com.account.dto.HttpClientResponseDto;
import com.account.http.AccountFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class FeignClientController {
    @Autowired
    private AccountFeign accountFeign;

    @GetMapping("/feign")
    public ResponseEntity<HttpClientResponseDto> getAccount() {
        ResponseEntity<HttpClientResponseDto> response = accountFeign.getCard();
        response.getBody().getResponse().put("ACCOUNT", "Feign Client From Account Service");
        return response;
    }

}