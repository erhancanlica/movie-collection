package com.spring.moviecollection.model;

import com.spring.moviecollection.model.enums.UserType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 25)
    @Column(name = "user_name", unique = true)
    private String username;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "user")
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Admins> admins = new HashSet<>();

}
