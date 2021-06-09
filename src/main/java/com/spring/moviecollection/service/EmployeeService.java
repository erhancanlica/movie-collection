package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Employee;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.model.dto.GeneralResponse;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {


    Employee findByUser(Users user);

    Optional<Employee> findById(Long id);

    List<CreateUserDto> getAll();

    GeneralResponse createEmploye(CreateUserDto createUserDto);

    void updateEmployee(CreateUserDto userDto, Users user);

    void deleteEmployee(CreateUserDto userDto);

    void deleteById(Long id);
}
