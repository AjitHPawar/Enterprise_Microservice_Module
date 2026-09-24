package com.user.controller.synchronous;

import com.user.dto.HttpClientResponseDto;
import com.user.http.UserFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class FeignClientController {
    @Autowired
    private UserFeign userFeign;

    @GetMapping("/feign")
    public ResponseEntity<HttpClientResponseDto> getUser() {
        ResponseEntity<HttpClientResponseDto> response = userFeign.getAccount();
        response.getBody().getResponse().put("USER", "Feign Client From User Service");
        return response;
    }

}
