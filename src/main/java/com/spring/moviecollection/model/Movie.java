package com.spring.moviecollection.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @Column(name = "publication_year", nullable = false)
    private String publicationYear;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "media")
    private String media;

    @Column(name = "language_options")
    private String languageOptions ;

    @OneToMany(mappedBy = "movie")
    private Set<Actor> actors = new HashSet<>();

}
