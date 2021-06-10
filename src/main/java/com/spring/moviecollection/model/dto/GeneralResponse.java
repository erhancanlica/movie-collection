package com.spring.moviecollection.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GeneralResponse {
    String message;
    int result;
}
