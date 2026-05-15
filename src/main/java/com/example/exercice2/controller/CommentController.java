package com.example.exercice2.controller;

import com.example.exercice2.DTO.CommentResponse;
import com.example.exercice2.entity.Comment;
import com.example.exercice2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    public CommentResponse createComment(
            @RequestBody Comment comment
    ) {

        return service.createComment(comment);
    }

    @GetMapping
    public List<CommentResponse> getAllComments() {

        return service.getAllComments();
    }

    @GetMapping("/{id}")
    public CommentResponse getCommentById(
            @PathVariable Long id
    ) {

        return service.getCommentById(id);
    }

    @PutMapping("/{id}")
    public CommentResponse updateComment(
            @PathVariable Long id,
            @RequestBody Comment comment
    ) {

        return service.updateComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public String deleteComment(
            @PathVariable Long id
    ) {

        service.deleteComment(id);

        return "Comment deleted successfully";
    }
}