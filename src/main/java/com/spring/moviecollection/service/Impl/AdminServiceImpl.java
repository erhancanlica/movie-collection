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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
                generalResponse = createAdmin(userDto);
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
    public GeneralResponse updateUser(CreateUserDto userDto) {
        GeneralResponse response = new GeneralResponse();

        Users user = Users.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .userType(userDto.getUserType())
                .build();

            try {
                if (UserType.ADMIN.equals(user.getUserType())) {

                    Admins admin = Admins.builder()
                            .id(userDto.getUserId())
                            .adminName(userDto.getRoleName())
                            .user(user)
                            .build();


                    adminRepository.save(admin);
                    response.setMessage("update successful");
                    response.setResult(0);
                }
                else if(UserType.EMPLOYEE.equals(user.getUserType())) {
                    employeeService.updateEmployee(userDto, user);
                    response.setMessage("update successful");
                    response.setResult(0); }
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
                        .userType(UserType.ADMIN)
                        .build();

                Admins admin = Admins.builder()
                        .id(userDto.getUserId())
                        .adminName(userDto.getRoleName())
                        .user(user)
                        .build();

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


    private GeneralResponse createAdmin(CreateUserDto userDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        try {
            Users user = Users.builder()
                    .username(userDto.getUsername())
                    .password(userDto.getPassword())
                    .userType(userDto.getUserType())
                    .build();

            userRepository.save(user);

            Admins admins = Admins.builder()
                    .adminName(userDto.getRoleName())
                    .user(user)
                    .build();

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


}
