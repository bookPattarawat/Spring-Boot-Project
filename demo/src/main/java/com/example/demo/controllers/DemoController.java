package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    UsersController userService = new UsersController();
    StudentsController studentService = new StudentsController();
    UsersController usersService = new UsersController();

    @RequestMapping("/login")
    public String showLoginForm(Model model) {
        return "login"; 
    }

    @RequestMapping("/register")
    public String showRegisterForm(Model model) {
        return "register"; 
    }

    @RequestMapping("/user/list")
    public String viewUserList(Model model) {
        return "userList";
    }

    @RequestMapping("/user/add")
    public String viewUserAdd(Model model) {
        return "userAdd";
    }

    @RequestMapping("/student/list")
    public String viewStudentList(Model model) {
        return "studentList";
    }

    @RequestMapping("/student/add")
    public String viewStudentAdd(Model model) {
        return "studentAdd";
    }
}
