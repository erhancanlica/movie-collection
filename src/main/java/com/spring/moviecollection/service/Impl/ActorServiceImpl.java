package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Actor;
import com.spring.moviecollection.model.Movie;
import com.spring.moviecollection.model.dto.ActorDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.repository.ActorRepository;
import com.spring.moviecollection.repository.MovieRepository;
import com.spring.moviecollection.service.ActorService;
import com.spring.moviecollection.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<ActorDto> findByMovieId(Long id) {
        try{
            List<Optional<Actor>> actors = actorRepository.findByMovieId(id);
        }catch (Exception ex){

        }
        return null;
    }

    @Override
    public GeneralResponse createActor(ActorDto actorDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        Optional<Movie> optionalMovie = movieRepository.findById(actorDto.getMovieID());
        Movie movie = null;

        if (optionalMovie.isPresent())
               movie = optionalMovie.get();
        else
            throw new RuntimeException("Movie not found");

        Actor actor = Actor.builder()
                .id(actorDto.getActorID())
                .movie(movie)
                .firstName(actorDto.getFirstName())
                .lastName(actorDto.getLastName())
                .role(actorDto.getRole()).build();

        try {
            actorRepository.save(actor);
            response.setMessage(Constants.success);
            response.setResult(0);

        }catch (Exception ex){
            response.setMessage(Constants.err);
            response.setResult(1);
        }
        return response;
    }

    @Override
    public GeneralResponse updateActor(ActorDto actorDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        Optional<Movie> optionalMovie = movieRepository.findById(actorDto.getMovieID());
        Movie movie = null;

        if (optionalMovie.isPresent())
            movie = optionalMovie.get();
        else
            throw new RuntimeException("Movie not found");

        Actor actor = Actor.builder()
                .id(actorDto.getActorID())
                .movie(movie)
                .firstName(actorDto.getFirstName())
                .lastName(actorDto.getLastName())
                .role(actorDto.getRole()).build();
        try {
            actorRepository.save(actor);
            response.setMessage(Constants.success);
            response.setResult(0);

        }catch (Exception ex){
            response.setMessage(Constants.err);
            response.setResult(1);
        }
        return response;
    }

    @Override
    public GeneralResponse deleteActor(Long id) {
        GeneralResponse response = GeneralResponse.builder().build();
        try {
            actorRepository.deleteById(id);
            response.setMessage(Constants.success);
            response.setResult(0);

        }catch (Exception ex){
            response.setMessage(Constants.err);
            response.setResult(1);
        }
        return response;
    }
}
