package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Movie;
import com.spring.moviecollection.model.dto.CreateMovieDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.model.dto.MovieDto;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface MovieService {

    MovieDto findById(Long id);

    List<MovieDto> findAll();

    GeneralResponse createMovie(CreateMovieDto createMovieDto);

    GeneralResponse updateMovie(CreateMovieDto createMovieDto);

    GeneralResponse deleteMovie(Long id);


}
