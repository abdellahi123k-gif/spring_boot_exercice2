package com.example.exercice2.service;

import com.example.exercice2.DTO.CommentResponse;
import com.example.exercice2.entity.Comment;
import com.example.exercice2.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    public CommentResponse createComment(Comment comment) {

        Comment saved = repository.save(comment);

        return toDto(saved);
    }

    public List<CommentResponse> getAllComments() {

        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public CommentResponse getCommentById(Long id) {

        Comment comment = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found"));

        return toDto(comment);
    }

    public CommentResponse updateComment(
            Long id,
            Comment updatedComment
    ) {

        Comment existingComment = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found"));

        existingComment.setMessage(updatedComment.getMessage());

        Comment saved = repository.save(existingComment);

        return toDto(saved);
    }

    public void deleteComment(Long id) {

        Comment comment = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found"));

        repository.delete(comment);
    }

    public CommentResponse toDto(Comment comment) {

        CommentResponse dto = new CommentResponse();

        dto.setId(comment.getId());
        dto.setMessage(comment.getMessage());

        if (comment.getArticle() != null) {

            dto.setArticleId(
                    comment.getArticle().getId()
            );
        }

        return dto;
    }
}