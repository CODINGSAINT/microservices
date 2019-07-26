package com.codingsaint.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.codingsaint.microservices.model.User;

@Repository
@RestResource
public interface UserRepository extends JpaRepository<User, Long> {

}
