package com.christo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.christo.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

}