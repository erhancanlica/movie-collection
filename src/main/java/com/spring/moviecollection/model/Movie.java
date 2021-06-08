package com.spring.moviecollection.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "media")
    private String media;

    @Lob
    @Column(name = "data")
    private byte[] image;

    @OneToMany(mappedBy = "movie")
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "movie_category",
            joinColumns = { @JoinColumn(name = "movie_id")},
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private List<Category> category = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_language",
            joinColumns = { @JoinColumn(name = "movie_id")},
            inverseJoinColumns = { @JoinColumn(name = "language_options_id") })
    private List<LanguageOption> language = new ArrayList<>();
}
