package com.spring.moviecollection.repository;

import com.spring.moviecollection.model.Admins;
import com.spring.moviecollection.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Long> {

}
