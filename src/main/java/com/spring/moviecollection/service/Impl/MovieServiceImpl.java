package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Actor;
import com.spring.moviecollection.model.Category;
import com.spring.moviecollection.model.LanguageOption;
import com.spring.moviecollection.model.Movie;
import com.spring.moviecollection.model.dto.*;
import com.spring.moviecollection.repository.CategoryRepository;
import com.spring.moviecollection.repository.LanguageOptionRepository;
import com.spring.moviecollection.repository.MovieRepository;
import com.spring.moviecollection.service.MovieService;
import com.spring.moviecollection.utils.Constants;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LanguageOptionRepository languageOptionRepository;

    @Override
    public MovieDto findById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        Movie movie = null;
        if (optionalMovie.isPresent())
            movie = optionalMovie.get();
        else
            throw new RuntimeException("Movie not found for id : " + id);

        MovieDto movieDto =MovieDto.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .publicationYear(movie.getPublicationYear())
                .explanation(movie.getExplanation())
                .media(movie.getMedia())
                .category(movie.getCategory().stream().map((Category category) -> CategoryDto
                        .builder()
                        .id(category.getId())
                        .categoryName(category.getCategoryName())
                        .build()).collect(Collectors.toList()))
                .language(movie.getLanguage().stream().map((LanguageOption languageOption) -> LanguageDto
                        .builder()
                        .id(languageOption.getId())
                        .language(languageOption.getLanguage())
                        .build()).collect(Collectors.toList()))
                .actors(movie.getActors().stream().map((Actor actor) -> ActorDto
                        .builder()
                        .actorID(actor.getId())
                        .movieID(actor.getMovie().getId())
                        .firstName(actor.getFirstName())
                        .lastName(actor.getLastName())
                        .role(actor.getRole())
                        .build()).collect(Collectors.toList()))
                .build();

        return movieDto;
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = movieRepository.findAll();
       return EntityToDto(movies);
    }

    @Override
    public GeneralResponse createMovie(CreateMovieDto createMovieDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        List<Long> category = createMovieDto.getCategory();
        List<Long> language = createMovieDto.getLanguage();

        List<Category> categories = categoryRepository.findAllById(category);
        List<LanguageOption> languages = languageOptionRepository.findAllById(language);

        Movie movie = Movie.builder()
                .movieName(createMovieDto.getMovieName())
                .publicationYear(createMovieDto.getPublicationYear())
                .explanation(createMovieDto.getExplanation())
                .media(createMovieDto.getMedia())
                .category(categories)
                .language(languages)
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
    public GeneralResponse updateMovie(CreateMovieDto createMovieDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        List<Long> category = createMovieDto.getCategory();
        List<Long> language = createMovieDto.getLanguage();

        List<Category> categories = categoryRepository.findAllById(category);
        List<LanguageOption> languages = languageOptionRepository.findAllById(language);

        Movie movie = Movie.builder()
                .id(createMovieDto.getId())
                .movieName(createMovieDto.getMovieName())
                .publicationYear(createMovieDto.getPublicationYear())
                .explanation(createMovieDto.getExplanation())
                .media(createMovieDto.getMedia())
                .category(categories)
                .language(languages)
                .build();

        try{
            movieRepository.save(movie);
            response.setResult(0);
            response.setMessage(Constants.success);
        }catch (Exception ex){
            response.setResult(1);
            response.setMessage(Constants.err);
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

    @Override
    public List<MovieDto> sortByMovieOrYear(String value) {
        Sort sortOrder = Sort.by(value);
        List<Movie> movies = movieRepository.findAll(sortOrder);
        return EntityToDto(movies);
    }

    @Override
    public List<MovieDto> findBySearch(String value) {
    List<Movie> movies =  movieRepository.findBySearch(value);
     return EntityToDto(movies);
    }

    private List<MovieDto> EntityToDto(List<Movie> movies){
        return movies.stream().map((Movie movie1) -> MovieDto.builder()
                .id(movie1.getId())
                .movieName(movie1.getMovieName())
                .publicationYear(movie1.getPublicationYear())
                .explanation(movie1.getExplanation())
                .media(movie1.getMedia())
                .category(movie1.getCategory().stream().map((Category category) -> CategoryDto
                        .builder()
                        .id(category.getId())
                        .categoryName(category.getCategoryName())
                        .build()).collect(Collectors.toList()))
                .language(movie1.getLanguage().stream().map((LanguageOption languageOption) -> LanguageDto
                        .builder()
                        .id(languageOption.getId())
                        .language(languageOption.getLanguage())
                        .build()).collect(Collectors.toList()))
                .actors(movie1.getActors().stream().map((Actor actor) -> ActorDto
                        .builder()
                        .actorID(actor.getId())
                        .movieID(actor.getMovie().getId())
                        .firstName(actor.getFirstName())
                        .lastName(actor.getLastName())
                        .role(actor.getRole())
                        .build()).collect(Collectors.toList()))
                .build())
                .collect(Collectors.toList());
    }
}
