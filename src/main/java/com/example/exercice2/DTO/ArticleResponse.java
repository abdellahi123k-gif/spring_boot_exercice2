package com.example.exercice2.DTO;

import lombok.Data;

import java.util.List;
@Data
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private UserResponse user;
    private List<CommentResponse> comments;
}