package com.example.exercice2.controller;

import com.example.exercice2.DTO.ArticleResponse;
import com.example.exercice2.entity.Article;
import com.example.exercice2.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @PostMapping
    public ArticleResponse createArticle(
            @RequestBody Article article
    ) {
        return service.createArticle(article);
    }

    @GetMapping
    public List<ArticleResponse> getAllArticles() {
        return service.getAllArticles();
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticleById(
            @PathVariable Long id
    ) {
        return service.getArticleById(id);
    }

    @PutMapping("/{id}")
    public ArticleResponse updateArticle(
            @PathVariable Long id,
            @RequestBody Article article
    ) {

        return service.updateArticle(id, article);
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(
            @PathVariable Long id
    ) {

        service.deleteArticle(id);

        return "Article deleted successfully";
    }
}