package com.springbootmicroservices.Userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootmicroservices.Userservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
