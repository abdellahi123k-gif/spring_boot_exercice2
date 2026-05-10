package com.example.exercice2.controller;

import com.example.exercice2.entity.Comment;
import com.example.exercice2.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return service.createComment(comment);
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return service.getAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return service.getCommentById(id);
    }
}