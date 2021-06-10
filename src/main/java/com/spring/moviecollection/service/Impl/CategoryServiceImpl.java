package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Category;
import com.spring.moviecollection.model.dto.CategoryDto;
import com.spring.moviecollection.repository.CategoryRepository;
import com.spring.moviecollection.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {

        List<Category> categories = categoryRepository.findAll();

        List<CategoryDto> categoryDt = categories.stream().map((Category category) -> CategoryDto
                .builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build()).collect(Collectors.toList());

        return categoryDt;
    }
}
