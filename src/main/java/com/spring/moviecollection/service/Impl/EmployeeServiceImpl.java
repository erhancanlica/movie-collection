package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Employee;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.repository.AdminRepository;
import com.spring.moviecollection.repository.EmployeeRepository;
import com.spring.moviecollection.service.EmployeeService;
import com.spring.moviecollection.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final AdminRepository adminRepository;

    @Override
    public Employee findByUser(Users user) {
        return null;//employeeRepository.findByUser(user);
    }

    @Override // buraya bak
    public GeneralResponse createEmploye(CreateUserDto createUserDto) {
        GeneralResponse response = GeneralResponse.builder().build();
        return response;

    }

    @Override
    public List<CreateUserDto> getAllEmployee() {
        return null;
    }
}
