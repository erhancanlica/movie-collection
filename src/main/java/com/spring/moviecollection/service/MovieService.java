package com.spring.moviecollection.service;

import com.spring.moviecollection.model.dto.CreateMovieDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.model.dto.MovieDto;

import java.util.List;

public interface MovieService {

    MovieDto findById(Long id);

    List<MovieDto> findAll();

    GeneralResponse createMovie(CreateMovieDto createMovieDto);

    GeneralResponse updateMovie(CreateMovieDto createMovieDto);

    GeneralResponse deleteMovie(Long id);

    List<MovieDto> sortByMovieOrYear(String value);

    List<MovieDto> findBySearch(String value);
}
