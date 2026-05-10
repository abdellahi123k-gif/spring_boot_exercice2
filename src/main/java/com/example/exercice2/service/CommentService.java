package com.example.exercice2.service;

import com.example.exercice2.entity.Comment;
import com.example.exercice2.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public Comment createComment(Comment comment) {
        return repository.save(comment);
    }

    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    public Comment getCommentById(Long id) {
        return repository.findById(id).orElse(null);
    }
}