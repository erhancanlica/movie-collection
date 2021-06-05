package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Users;
import com.spring.moviecollection.repository.UserRepository;
import com.spring.moviecollection.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Users findByUserName(String username) {
        //Users user = userRepository.findByUsername(username);
        return null;
    }
}
