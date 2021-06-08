package com.spring.moviecollection.service;

import com.spring.moviecollection.model.Actor;
import com.spring.moviecollection.model.dto.ActorDto;
import com.spring.moviecollection.model.dto.GeneralResponse;

import java.util.List;

public interface ActorService {

    List<ActorDto> findByMovieId(Long id);
}
