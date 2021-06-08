package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Movie;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.model.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();

    GeneralResponse createMovie(MovieDto movieDto);

    GeneralResponse updateMovie(MovieDto movieDto);

    GeneralResponse deleteMovie(Long id);


}
