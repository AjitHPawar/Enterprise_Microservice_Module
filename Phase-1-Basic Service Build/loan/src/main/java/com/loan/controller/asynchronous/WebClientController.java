package com.loan.controller.asynchronous;

import com.loan.dto.HttpClientResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/loan")
public class WebClientController {

    @GetMapping("/web-client")
    public HttpClientResponseDto getLoan() {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("LOAN", "Async-WebClient From Loan Service");
        return HttpClientResponseDto.builder()
                .response(responseMap)
                .build();
    }

}
