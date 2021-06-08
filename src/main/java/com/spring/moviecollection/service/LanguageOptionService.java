package com.spring.moviecollection.service;

import com.spring.moviecollection.model.dto.LanguageDto;

import java.util.List;

public interface LanguageOptionService {
    List<LanguageDto> findAll();
}
