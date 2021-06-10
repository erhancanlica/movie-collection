package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Employee;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.model.dto.GeneralResponse;

import java.util.List;

public interface EmployeeService {

    Employee findById(Long id);

    Employee findByUser(Users user);

    List<CreateUserDto> getAll();

    GeneralResponse createEmployee(CreateUserDto createUserDto);

    void updateEmployee(CreateUserDto userDto, Users user);

    void deleteEmployee(CreateUserDto userDto);

}
