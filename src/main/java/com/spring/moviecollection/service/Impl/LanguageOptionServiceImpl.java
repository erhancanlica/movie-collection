package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.LanguageOption;
import com.spring.moviecollection.model.dto.LanguageDto;
import com.spring.moviecollection.repository.LanguageOptionRepository;
import com.spring.moviecollection.service.LanguageOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class LanguageOptionServiceImpl implements LanguageOptionService {

    private final LanguageOptionRepository languageOptionRepository;

    public LanguageOptionServiceImpl(LanguageOptionRepository languageOptionRepository) {
        this.languageOptionRepository = languageOptionRepository;
    }

    @Override
    public List<LanguageDto> findAll() {
        List<LanguageOption> languageOptions = languageOptionRepository.findAll();

        List<LanguageDto> languageDtos = languageOptions.stream().map((LanguageOption language) -> LanguageDto
                                                        .builder()
                                                        .id(language.getId())
                                                        .language(language.getLanguage())
                                                        .build()).collect(Collectors.toList());

        return languageDtos;
    }
}
