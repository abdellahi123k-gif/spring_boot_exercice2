package com.example.exercice2.service;

import com.example.exercice2.DTO.UserResponse;
import com.example.exercice2.entity.User;
import com.example.exercice2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        User saved = repository.save(user);

        return toDto(saved);
    }

    public List<UserResponse> getAllUsers() {

        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public UserResponse getUserById(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return toDto(user);
    }

    public UserResponse updateUser(
            Long id,
            User updatedUser
    ) {

        User existingUser = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());

        if (updatedUser.getPassword() != null &&
                !updatedUser.getPassword().isEmpty()) {

            existingUser.setPassword(
                    passwordEncoder.encode(
                            updatedUser.getPassword()
                    )
            );
        }

        User saved = repository.save(existingUser);

        return toDto(saved);
    }

    public void deleteUser(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        repository.delete(user);
    }

    public UserResponse toDto(User user) {

        UserResponse dto = new UserResponse();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());

        return dto;
    }
}