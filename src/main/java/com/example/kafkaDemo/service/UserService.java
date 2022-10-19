package com.example.kafkaDemo.service;

import com.example.kafkaDemo.bean.User;
import com.example.kafkaDemo.repo.UserCrudRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserCrudRepo userCrudRepo;

    @PostConstruct
    public void setupDefault() {
        saveUser(User.builder().name("John").build());
        saveUser(User.builder().name("Mike").build());

    }

    public User saveUser(User user) {
        return userCrudRepo.save(user);
    }

    public void deleteUser(Long id) {
        userCrudRepo.deleteById(id);
    }

    public List<User> getAllUsers() {
        return (List<User>) userCrudRepo.findAll();
    }

    public User getUser(Long id) {
        return userCrudRepo
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Can not find any user by ID:%s", id)));
    }



}
