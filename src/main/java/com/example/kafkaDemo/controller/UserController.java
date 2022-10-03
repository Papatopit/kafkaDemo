package com.example.kafkaDemo.controller;

import com.example.kafkaDemo.bean.User;
import com.example.kafkaDemo.repo.UserCrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserCrudRepo userCrudRepo;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        List<User> users = (List<User>) userCrudRepo.findAll();
        return users;
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        user = userCrudRepo.save(user);
        return user;
    }

}
