package com.spring.moviecollection.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {
    public Long id;
    private String language;
}
