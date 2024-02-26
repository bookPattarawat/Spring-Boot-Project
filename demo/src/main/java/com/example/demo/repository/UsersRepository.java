package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.UsersEntity;


public interface UsersRepository extends CrudRepository<UsersEntity,Integer> {

    @Query(value = "SELECT * FROM std6.users " +
               "WHERE std6.users.username = ?1 AND std6.users.password = ?2", nativeQuery = true)
    UsersEntity login(String username, String password);
}
