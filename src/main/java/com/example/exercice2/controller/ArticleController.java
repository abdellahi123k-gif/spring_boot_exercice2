package com.example.exercice2.controller;

import com.example.exercice2.entity.Article;
import com.example.exercice2.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return service.createArticle(article);
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return service.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return service.getArticleById(id);
    }
}