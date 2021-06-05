package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Users;

public interface UserService {

    Users findByUserName(String username);
}
