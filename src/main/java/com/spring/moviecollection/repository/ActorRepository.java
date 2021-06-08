package com.spring.moviecollection.repository;

import com.spring.moviecollection.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Optional<Actor>> findByMovieId(Long id);
}
