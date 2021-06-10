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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@Transactional
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    private final MovieRepository movieRepository;

    public ActorServiceImpl(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public GeneralResponse createActor(ActorDto actorDto) {
        return getGeneralResponse(actorDto);
    }

    @Override
    public GeneralResponse updateActor(ActorDto actorDto) {
        return getGeneralResponse(actorDto);
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

    private GeneralResponse getGeneralResponse(ActorDto actorDto) {
        GeneralResponse response = GeneralResponse.builder().build();

        Actor nonNullactor = actorRepository.findByFirstNameAndLastName(actorDto.getMovieID(), actorDto.getFirstName(), actorDto.getLastName());
        if (nonNull(nonNullactor)) {
            return GeneralResponse.builder()
                    .message("Actor is already in use.")
                    .result(1)
                    .build();
        }

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
            log.error(ex.getLocalizedMessage());
            response.setMessage(Constants.err);
            response.setResult(1);
        }
        return response;
    }


}
