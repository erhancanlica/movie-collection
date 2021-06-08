package com.spring.moviecollection.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.moviecollection.model.Actor;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Long id;

    private String movieName;

    private String publicationYear;

    private String explanation;

    private String media;

    private MultipartFile chooseFile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private byte[] image;

    private List<CategoryDto> category = new ArrayList<>();

    private List<LanguageDto> language = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Actor> actors = new HashSet<>();


}
