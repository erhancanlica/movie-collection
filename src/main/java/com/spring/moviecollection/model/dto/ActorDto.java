package com.spring.moviecollection.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto {
    private Long actorID;
    private Long movieID;
    private String firstName;
    private String lastName;
    private String role;

    @Override
    public String toString() {
        return firstName + " " + lastName ;
    }
}
