package com.spring.moviecollection.service;

import com.spring.moviecollection.model.dto.ActorDto;
import com.spring.moviecollection.model.dto.GeneralResponse;

public interface ActorService {

    GeneralResponse createActor(ActorDto actorDto);

    GeneralResponse updateActor(ActorDto actorDto);

    GeneralResponse deleteActor(Long id);
}
