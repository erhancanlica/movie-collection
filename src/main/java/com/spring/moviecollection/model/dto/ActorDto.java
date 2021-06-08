package com.spring.moviecollection.model.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto {

    private Long id;

    private Long movieId;

    private String firstName;

    private String lastName;

    private String role;
}
