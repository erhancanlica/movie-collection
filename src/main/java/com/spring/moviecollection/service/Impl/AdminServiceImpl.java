package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Admins;
import com.spring.moviecollection.model.Employee;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.model.enums.UserType;
import com.spring.moviecollection.repository.AdminRepository;
import com.spring.moviecollection.repository.UserRepository;
import com.spring.moviecollection.service.AdminService;
import com.spring.moviecollection.service.EmployeeService;
import com.spring.moviecollection.service.UserService;
import com.spring.moviecollection.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final UserRepository userRepository;

    private final AdminService adminService;

    private final EmployeeService employeeService;

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Admins findByUser(Users user) {
        return  adminRepository.findByUser(user);
    }

    @Override
    public List<CreateUserDto> getAll() {
        List<Admins> admins = adminRepository.findAll();
        List<CreateUserDto> userDtos = admins.stream().map((Admins admin)-> CreateUserDto.builder()
                .id(admin.getUser().getId())
                .userId(admin.getId())
                .username(admin.getUser().getUsername())
                .password(admin.getUser().getPassword())
                .userType(admin.getUser().getUserType())
                .roleName(admin.getAdminName())
                .build())
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public GeneralResponse createUser(CreateUserDto userDto) {
        GeneralResponse generalResponse = null;

        Users user = userService.findByUserName(userDto.getUsername());
        if (nonNull(user)) {
            return GeneralResponse.builder()
                    .message("Username is already in use.")
                    .result(1)
                    .build();
        }
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        if (UserType.EMPLOYEE.equals(userDto.getUserType())) {
            try {
                generalResponse = employeeService.createEmploye(userDto);
                generalResponse.setMessage(Constants.success);
                generalResponse.setResult(0);
            }catch (Exception ex){
                generalResponse.setMessage(Constants.err);
                generalResponse.setResult(1);
            }
        }

        else if (UserType.ADMIN.equals(userDto.getUserType())){
            try {
                generalResponse = adminService.createAdmin(userDto);
                generalResponse.setMessage(Constants.success);
                generalResponse.setResult(0);
            }catch (Exception ex){
                generalResponse.setMessage(Constants.err);
                generalResponse.setResult(1);
            }
        }
        return generalResponse;
    }

    @Override
    public GeneralResponse createAdmin(CreateUserDto userDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        Users user = Users.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();

        Admins admins = Admins.builder()
                .id(userDto.getUserId())
                .adminName(userDto.getRoleName())
                .user(user)
                .build();
        try {
            userRepository.save(user);
            adminRepository.save(admins);
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
    public GeneralResponse updateUser(CreateUserDto userDto) {
        GeneralResponse response = new GeneralResponse();

            try {
                employeeService.updateEmployee(userDto);
                response.setMessage("update successful");
                response.setResult(0);

            } catch (Exception e) {

                response.setMessage("update failed");
                response.setResult(1);
            }

        return response;
    }

    @Override
    public GeneralResponse deleteUser(CreateUserDto userDto) {
        GeneralResponse response = new GeneralResponse();

        if (UserType.EMPLOYEE.equals(userDto.getUserType())){
            try {
                employeeService.deleteEmployee(userDto);
                response.setMessage("deletion successful");
                response.setResult(0);

            } catch (Exception e) {

                response.setMessage("deletion failed");
                response.setResult(1);
            }
        }

        else if (UserType.ADMIN.equals(userDto.getUserType())){
            try {
                Users user = Users.builder()
                        .id(userDto.getId())
                        .username(userDto.getUsername())
                        .password(userDto.getPassword())
                        .userType(userDto.getUserType())
                        .build();

                Admins admin = Admins.builder()
                        .id(userDto.getUserId())
                        .adminName(userDto.getRoleName())
                        .user(user)
                        .build();

                userRepository.delete(user);
                adminRepository.delete(admin);
                response.setMessage("deletion successful");
                response.setResult(0);

            } catch (Exception e) {

                response.setMessage("deletion failed");
                response.setResult(1);
            }
        }
        return response;
    }
}
