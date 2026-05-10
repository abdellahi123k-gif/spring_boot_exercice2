package com.example.exercice2.service;

import com.example.exercice2.entity.Article;
import com.example.exercice2.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public Article createArticle(Article article) {
        return repository.save(article);
    }

    public List<Article> getAllArticles() {
        return repository.findAll();
    }

    public Article getArticleById(Long id) {
        return repository.findById(id).orElse(null);
    }
}