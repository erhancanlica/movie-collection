package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Admins;
import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.repository.AdminRepository;
import com.spring.moviecollection.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admins findByUser(Users user) {
        return new Admins(); //adminRepository.findByUser(user);
    }
}
