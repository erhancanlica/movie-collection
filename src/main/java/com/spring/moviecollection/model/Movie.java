package com.spring.moviecollection.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 25)
    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @NotNull
    @Column(name = "publication_year", nullable = false)
    private String publicationYear;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "explanation")
    private String explanation;

        @NotEmpty
    @Size(max = 25)
    @Column(name = "media")
    private String media;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private List<Actor> actors = new ArrayList<>();

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
