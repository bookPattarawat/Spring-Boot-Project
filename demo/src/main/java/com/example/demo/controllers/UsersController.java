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

import com.example.demo.entity.UsersEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.UsersRepository;

@RestController
public class UsersController {
   
    @Autowired
    UsersRepository repo;

     // login users
    // localhost:8080/users/login
    @PostMapping("/api/users/login")
    public UsersEntity userLogin(@RequestBody UsersEntity entity) {
        System.out.println("userLogin entity : " + entity);
        System.out.println("userLogin repo : " + repo);
        
        UsersEntity user = new UsersEntity();
        try {
            user = repo.login(entity.getUsername(), entity.getPassword());
            if (user == null) {
                user = new UsersEntity();
            }
            System.out.println("userLogin repo : " + user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            user = null;
        }
        return user;

    }

    // get all users
    // localhost:8080/users
    @GetMapping("/api/users")
    public List<UsersEntity> getAllUsers(){
        List<UsersEntity> users = new ArrayList<>();
        repo.findAll().forEach(users::add);
        return users;
    }

    // get by id users
    // localhost:8080/users/1
    @GetMapping("/api/users/{id}")
    public Optional<UsersEntity> getUser(@PathVariable int id){
        return repo.findById(id);
    }

    // insert id users
    // localhost:8080/users/add
    @PostMapping("/api/users/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String postUsers(@RequestBody UsersEntity entity) {
        String result = "";
        if(entity != null) {
            try {
                repo.save(entity);
                result = "user added successfully";
            }catch (Exception e){
                e.printStackTrace();
                result = "error added user";
            }
        }
        return result;
    }

    // update id users
    // localhost:8080/users/update/1
    @PutMapping("/api/users/update/{id}")
    public String updateUsers(@PathVariable int id, @RequestBody UsersEntity entity) {
        String result = "";
        UsersEntity user = repo.findById(id).get();
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEmail(entity.getEmail());
        user.setRoleId(entity.getRoleId());
        repo.save(user);
        return result;
    }
    
    // delete id users
    // localhost:8080/users/delete/1
    @DeleteMapping("/api/users/delete/{id}")
    public String deleteUsers(@PathVariable int id) {
        String result = "";
        try {
            repo.deleteById(id);
            result = "user deleted";
        }catch (Exception e){
            e.printStackTrace();
            result = "error deleting user";
        }
        return result;
    }
}