package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.StudentsEntity;
import com.example.demo.repository.StudentsRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class StudentsController {
    
    @Autowired
    StudentsRepository repo;
    // get all students
    // localhost:8080/students
    @GetMapping("/api/students")
    public List<StudentsEntity> getAllStudents(){
        List<StudentsEntity> students = new ArrayList<>();
        repo.findAll().forEach(students::add);
        return students;
    }

    // get by id students
    // localhost:8080/students/1
    @GetMapping("/api/students/{id}")
    public Optional<StudentsEntity> getStudent(@PathVariable int id){
        return repo.findById(id);
    }

    // insert id students
    // localhost:8080/students/add
    @PostMapping("/api/students/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String postStudents(@RequestBody StudentsEntity entity) {
        String result = "";
        if(entity != null) {
            try {
                repo.save(entity);
                result = "student added successfully";
            }catch (Exception e){
                e.printStackTrace();
                result = "error added student";
            }
        }
        return result;
    }

    // update id students
    // localhost:8080/students/update/1
    @PutMapping("/api/students/update/{id}")
    public String updateStudents(@PathVariable int id, @RequestBody StudentsEntity entity) {
        String result = "";
        StudentsEntity student = repo.findById(id).get();
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        repo.save(student);
        return result;
    }
    
    // delete id students
    // localhost:8080/students/delete/1
    @DeleteMapping("/api/students/delete/{id}")
    public String deleteStudents(@PathVariable int id) {
        String result = "";
        try {
            repo.deleteById(id);
            result = "student deleted";
        }catch (Exception e){
            e.printStackTrace();
            result = "error deleting student";
        }
        return result;
    }
}
