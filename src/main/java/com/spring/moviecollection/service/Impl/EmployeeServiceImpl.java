package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Employee;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.model.enums.UserType;
import com.spring.moviecollection.repository.EmployeeRepository;
import com.spring.moviecollection.repository.UserRepository;
import com.spring.moviecollection.service.EmployeeService;
import com.spring.moviecollection.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final UserRepository userRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Employee findByUser(Users user) {
        return employeeRepository.findByUser(user);
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = null;
        if (optionalEmployee.isPresent())
            employee = optionalEmployee.get();

        return employee;
    }

    @Override
    public List<CreateUserDto> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<CreateUserDto> userDt = employees.stream().map((Employee employee)-> CreateUserDto.builder()
                .id(employee.getUser().getId())
                .userId(employee.getId())
                .username(employee.getUser().getUsername())
                .password(employee.getUser().getPassword())
                .userType(employee.getUser().getUserType())
                .roleName(employee.getEmployeeName())
                .build())
                .collect(Collectors.toList());
        return userDt;
    }

    @Override
    public GeneralResponse createEmployee(CreateUserDto userDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        try {
            Users user = Users.builder()
                    .username(userDto.getUsername())
                    .password(userDto.getPassword())
                    .userType(userDto.getUserType())
                    .build();

            userRepository.save(user);
            Employee employee = Employee.builder()
                    .id(userDto.getUserId())
                    .employeeName(userDto.getRoleName())
                    .user(user)
                    .build();
            employeeRepository.save(employee);
            response.setMessage(Constants.success);
            response.setResult(0);
        } catch (Exception ex) {
        log.error("Error creating employee user : {}", ex);
        response.setMessage(Constants.err);
        response.setResult(1);
         }

        return response;
    }

    @Override
    public void updateEmployee(CreateUserDto userDto, Users user) {
            Employee employee = Employee.builder()
                    .id(userDto.getUserId())
                    .employeeName(userDto.getRoleName())
                    .user(user)
                    .build();
            employeeRepository.save(employee);
    }


    @Override
    public void deleteEmployee(CreateUserDto userDto) {
        Users user = Users.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .userType(UserType.EMPLOYEE)
                .build();

            Employee employee = Employee.builder()
                    .id(userDto.getUserId())
                    .employeeName(userDto.getRoleName())
                    .user(user)
                    .build();

            employeeRepository.delete(employee);

    }


}
