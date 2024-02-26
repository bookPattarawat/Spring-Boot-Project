package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.RoleRepository;

@RestController
public class RoleController {
    @Autowired
    RoleRepository repo;
    // get all roles
    // localhost:8080/roles
    @GetMapping("/api/role")
    public List<RoleEntity> getAllRole(){
        System.out.println("Get all roles");
        List<RoleEntity> entity = new ArrayList<>();
        repo.findAll().forEach(entity::add);
        return entity;
    }

    @GetMapping("/api/role/{id}")
    public Optional<RoleEntity> getRole(@PathVariable int id){
        return repo.findById(id);
    }

    // insert id role
    // localhost:8080/role/add
    @PostMapping("/api/role/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String postRole(@RequestBody RoleEntity entity) {
        String result = "";
        if(entity != null) {
            try {
                repo.save(entity);
                result = "Role added successfully";
            }catch (Exception e){
                e.printStackTrace();
                result = "error added role";
            }
        }
        return result;
    }

    // update id role
    // localhost:8080/role/update/1
    @PutMapping("/api/role/update/{id}")
    public String updateRole(@PathVariable int id, @RequestBody RoleEntity entity) {
        String result = "";
        RoleEntity role = repo.findById(id).get();
        role.setRoleName(entity.getRoleName());
        repo.save(role);
        return result;
    }
    
    // delete id role
    // localhost:8080/role/delete/1
    @DeleteMapping("/api/role/delete/{id}")
    public String deleteRole(@PathVariable int id) {
        String result = "";
        try {
            repo.deleteById(id);
            result = "role deleted";
        }catch (Exception e){
            e.printStackTrace();
            result = "error deleting role";
        }
        return result;
    }
}
