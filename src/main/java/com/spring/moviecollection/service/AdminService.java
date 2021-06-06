package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Admins;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.model.dto.GeneralResponse;

import java.util.List;

public interface AdminService {

    List<CreateUserDto> getAll();

    Admins findByUser(Users user);

    GeneralResponse createUser(CreateUserDto userDto);

    GeneralResponse createAdmin(CreateUserDto userDto);

    GeneralResponse updateUser(CreateUserDto userDto);

    GeneralResponse deleteUser(CreateUserDto userDto);
}
