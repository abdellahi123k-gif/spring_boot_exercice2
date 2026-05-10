package com.example.exercice2.repository;

import com.example.exercice2.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository
        extends JpaRepository<Article, Long> {
}