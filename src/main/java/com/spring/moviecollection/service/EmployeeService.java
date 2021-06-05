package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Employee;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.model.dto.GeneralResponse;

import java.util.List;

public interface EmployeeService {

    Employee findByUser(Users user);

    GeneralResponse createEmploye(CreateUserDto createUserDto);

    List<CreateUserDto> getAllEmployee();
}
