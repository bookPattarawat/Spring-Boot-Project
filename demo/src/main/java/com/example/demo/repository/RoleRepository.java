package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity,Integer> {
    
}
