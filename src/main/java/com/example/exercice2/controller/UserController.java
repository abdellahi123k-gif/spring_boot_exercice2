package com.example.exercice2.controller;

import com.example.exercice2.DTO.UserResponse;
import com.example.exercice2.entity.User;
import com.example.exercice2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserResponse createUser(
            @RequestBody User user
    ) {

        return service.createUser(user);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {

        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(
            @PathVariable Long id
    ) {

        return service.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody User user
    ) {

        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable Long id
    ) {

        service.deleteUser(id);

        return "User deleted successfully";
    }
}