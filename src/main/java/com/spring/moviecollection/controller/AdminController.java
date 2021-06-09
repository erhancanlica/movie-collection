package com.spring.moviecollection.controller;

import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.model.dto.CreateUserDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.model.enums.UserType;
import com.spring.moviecollection.service.AdminService;
import com.spring.moviecollection.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAll(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("user/listUser");
        List<UserType> userTypeList = Arrays.asList(UserType.values());
        modelAndView.addObject("users",  userService.getAll());
        modelAndView.addObject("userRoles", userTypeList);
        return modelAndView;
    }

    @GetMapping("/createUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getCreateUser(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("user/createUser");
        List<UserType> userTypeList = Arrays.asList(UserType.values());
            modelAndView.addObject("users", new Users());
        modelAndView.addObject("userRoles", userTypeList);
        return modelAndView;
    }

    @PostMapping("/createUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public GeneralResponse createUser(@RequestBody CreateUserDto userDto){
        GeneralResponse response = adminService.createUser(userDto);
        return response;
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public GeneralResponse updateUser(@RequestBody CreateUserDto userDto){
        GeneralResponse response = adminService.updateUser(userDto);
        return response;
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public GeneralResponse deleteUser(@RequestBody CreateUserDto userDto){
        GeneralResponse response = adminService.deleteUser(userDto);
        return response;
    }
}
