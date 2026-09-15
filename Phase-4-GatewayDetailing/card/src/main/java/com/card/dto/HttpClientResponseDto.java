package com.card.dto;

import lombok.*;

import java.util.Map;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HttpClientResponseDto {

    private Map<String , String> response;

}
