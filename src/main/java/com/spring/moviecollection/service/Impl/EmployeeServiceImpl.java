package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Admins;
import com.spring.moviecollection.model.Employee;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.model.enums.UserType;
import com.spring.moviecollection.repository.AdminRepository;
import com.spring.moviecollection.repository.EmployeeRepository;
import com.spring.moviecollection.repository.UserRepository;
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

    private final UserRepository userRepository;

    @Override
    public Employee findByUser(Users user) {
        return null;//employeeRepository.findByUser(user);
    }

    @Override
    public List<CreateUserDto> getAllEmployee() {
        return null;
    }

    @Override
    public GeneralResponse createEmploye(CreateUserDto userDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        Users user = Users.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();

        Employee employee = Employee.builder()
                .id(userDto.getUserId())
                .employeeName(userDto.getRoleName())
                .user(user)
                .build();
        try {
            userRepository.save(user);
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
    public void updateEmployee(CreateUserDto userDto) {
        Users user = Users.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .userType(userDto.getUserType())
                .build();

        if (UserType.EMPLOYEE.equals(user.getUserType())) {
            Employee employee = Employee.builder()
                    .id(userDto.getUserId())
                    .employeeName(userDto.getRoleName())
                    .user(user)
                    .build();
            employeeRepository.save(employee);
        }
        else if (UserType.ADMIN.equals(user.getUserType())) {
            Admins admin = Admins.builder()
                    .id(userDto.getUserId())
                    .adminName(userDto.getRoleName())
                    .user(user)
                    .build();
            adminRepository.save(admin);
        }
    }

    @Override
    public void deleteEmployee(CreateUserDto userDto) {
        Users user = Users.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .userType(userDto.getUserType())
                .build();

            Employee employee = Employee.builder()
                    .id(userDto.getUserId())
                    .employeeName(userDto.getRoleName())
                    .user(user)
                    .build();

            userRepository.delete(user);
            employeeRepository.delete(employee);

    }

}
