package com.spring.moviecollection.repository;

import com.spring.moviecollection.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Optional<Actor>> findByMovieId(Long id);

    @Query(value = "SELECT * FROM actor a JOIN movie m ON LOWER(a.movie_id)= LOWER(:movieId)" +
            "WHERE LOWER(a.first_name)=(:firstName) AND LOWER(a.last_name)=(:lastName)", nativeQuery = true)
    Actor findByFirstNameAndLastName(Long movieId, String firstName, String lastName);
}
