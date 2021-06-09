package com.spring.moviecollection.repository;

import com.spring.moviecollection.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movie where LOWER(movie_name)=LOWER(:value)" +
            " OR id IN (SELECT a.movie_id FROM actor a WHERE LOWER(a.first_name) = LOWER(:value))" +
            " OR id IN (select mc.movie_id FROM movie_category mc WHERE mc.category_id = (SELECT c.id FROM category c WHERE LOWER(c.category_name)=LOWER(:value)))", nativeQuery = true)
    List<Movie> findBySearch(String value);
}
