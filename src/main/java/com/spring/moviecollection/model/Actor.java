package com.spring.moviecollection.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "actor")
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 25)
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Size(max = 25)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 25)
    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}
