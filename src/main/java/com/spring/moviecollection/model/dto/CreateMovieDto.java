package com.spring.moviecollection.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.moviecollection.model.Actor;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieDto {
    private Long id;
    private String movieName;
    private String publicationYear;
    private String explanation;
    private String media;
    private List<Long> category = new ArrayList<>();
    private List<Long> language = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Actor> actors = new HashSet<>();
}
