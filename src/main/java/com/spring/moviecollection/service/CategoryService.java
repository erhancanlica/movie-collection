package com.spring.moviecollection.service;

import com.spring.moviecollection.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
}
