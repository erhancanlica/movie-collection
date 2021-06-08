package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Movie;
import com.spring.moviecollection.model.dto.CategoryDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.model.dto.MovieDto;
import com.spring.moviecollection.repository.MovieRepository;
import com.spring.moviecollection.service.MovieService;
import com.spring.moviecollection.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDto> movieDtos = movies.stream().map((Movie movie) -> MovieDto.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .publicationYear(movie.getPublicationYear())
                .explanation(movie.getExplanation())
                .media(movie.getMedia())
                .actors(movie.getActors())
                .build())
                .collect(Collectors.toList());
        return movieDtos;
    }

    @Override
    public GeneralResponse createMovie(MovieDto movieDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        Movie movie = Movie.builder()
                .movieName(movieDto.getMovieName())
                .publicationYear(movieDto.getPublicationYear())
                .image(movieDto.getImage())
                .explanation(movieDto.getExplanation())
                .media(movieDto.getMedia())
                .build();

        try{
            movieRepository.save(movie);
            response.setMessage(Constants.success);
            response.setResult(0);
        }catch (Exception ex){
            response.setMessage(Constants.err);
            response.setResult(1);
        }
        return response;
    }

    @Override
    public GeneralResponse updateMovie(MovieDto movieDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        Movie movie = Movie.builder()
                .id(movieDto.getId())
                .movieName(movieDto.getMovieName())
                .explanation(movieDto.getExplanation())
                .media(movieDto.getMedia())
                .build();

        try{
            movieRepository.save(movie);
            response.setMessage(Constants.success);
            response.setResult(0);
        }catch (Exception ex){
            response.setMessage(Constants.err);
            response.setResult(1);
        }
        return response;
    }

    @Override
    public GeneralResponse deleteMovie(Long id) {
        GeneralResponse response = GeneralResponse.builder().build();
        try{
            movieRepository.deleteById(id);
            response.setMessage(Constants.success);
            response.setResult(0);
        }catch (Exception ex){
            response.setMessage(Constants.err);
            response.setResult(1);
        }
        return response;
    }
}
