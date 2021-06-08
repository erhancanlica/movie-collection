package com.spring.moviecollection.service.Impl;

import com.spring.moviecollection.model.Actor;
import com.spring.moviecollection.model.dto.ActorDto;
import com.spring.moviecollection.model.dto.GeneralResponse;
import com.spring.moviecollection.repository.ActorRepository;
import com.spring.moviecollection.service.ActorService;
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

    @Override
    public List<ActorDto> findByMovieId(Long id) {
        try{
            List<Optional<Actor>> actors = actorRepository.findByMovieId(id);
        }catch (Exception ex){

        }
        return null;
    }
}
