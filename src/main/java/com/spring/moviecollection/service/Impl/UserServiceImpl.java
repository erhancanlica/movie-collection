package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.repository.UserRepository;
import com.spring.moviecollection.service.AdminService;
import com.spring.moviecollection.service.EmployeeService;
import com.spring.moviecollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<CreateUserDto> getAll() {
        List<CreateUserDto> admins = adminService.getAll();
        List<CreateUserDto> employess = employeeService.getAll();

        admins.addAll(employess);
        return admins;
    }

    @Override
    public Users findByUserName(String username) {
        Users user = userRepository.findByUsername(username);
        return user;
    }
}
