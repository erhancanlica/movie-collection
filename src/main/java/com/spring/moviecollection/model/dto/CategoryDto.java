package com.spring.moviecollection.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    public Long id;

    private String categoryName;
}
