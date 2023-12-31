package com.anubhav.controller;

import com.anubhav.models.User;
import com.anubhav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getUsers(){
        return this.userService.getAllUsers();
    }
}
