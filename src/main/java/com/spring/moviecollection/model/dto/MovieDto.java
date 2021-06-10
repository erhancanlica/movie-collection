package com.spring.moviecollection.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MovieDto {
    private Long id;
    private String movieName;
    private int publicationYear;
    private String explanation;
    private String media;
    private List<CategoryDto> category = new ArrayList<>();
    private List<LanguageDto> language = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ActorDto> actors = new ArrayList<>();


}
