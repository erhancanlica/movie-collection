package com.spring.moviecollection.model.dto;

import com.spring.moviecollection.model.enums.UserType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private Long id;
    private Long userId;
    private String username;
    private String password;
    private UserType userType;
    private String roleName;
}
