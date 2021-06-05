package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Admins;
import com.spring.moviecollection.model.Users;

public interface AdminService {

    Admins findByUser(Users user);
}
