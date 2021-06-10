package com.spring.moviecollection.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LanguageDto {
    public Long id;
    private String language;

    @Override
    public String toString() {
        return language ;
    }


}
