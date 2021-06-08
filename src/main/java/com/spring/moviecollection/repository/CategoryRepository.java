package com.spring.moviecollection.repository;

import com.spring.moviecollection.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
