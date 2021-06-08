package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;

import java.util.List;

public interface UserService {

    List<CreateUserDto> getAll();

    Users findByUserName(String username);


}
