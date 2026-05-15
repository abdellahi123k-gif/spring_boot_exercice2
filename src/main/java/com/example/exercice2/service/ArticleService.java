package com.example.exercice2.service;

import com.example.exercice2.DTO.ArticleResponse;
import com.example.exercice2.DTO.CommentResponse;
import com.example.exercice2.DTO.UserResponse;
import com.example.exercice2.entity.Article;
import com.example.exercice2.entity.User;
import com.example.exercice2.repository.ArticleRepository;
import com.example.exercice2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;
    private final UserRepository userRepository;

    public ArticleResponse createArticle(Article article) {

        User user = userRepository.findById(
                article.getUser().getId()
        ).orElseThrow(() ->
                new RuntimeException("User not found"));

        article.setUser(user);

        Article savedArticle = repository.save(article);

        return toDto(savedArticle);
    }

    public List<ArticleResponse> getAllArticles() {

        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public ArticleResponse getArticleById(Long id) {

        Article article = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Article not found"));

        return toDto(article);
    }

    public ArticleResponse updateArticle(
            Long id,
            Article updatedArticle
    ) {

        Article existingArticle = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Article not found"));

        existingArticle.setTitle(updatedArticle.getTitle());
        existingArticle.setContent(updatedArticle.getContent());

        Article savedArticle = repository.save(existingArticle);

        return toDto(savedArticle);
    }

    public void deleteArticle(Long id) {

        Article article = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Article not found"));

        repository.delete(article);
    }

    private ArticleResponse toDto(Article article) {

        User user = article.getUser();

        UserResponse userDto = new UserResponse();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());

        List<CommentResponse> commentsDto =
                article.getComments() == null
                        ? List.of()
                        : article.getComments()
                        .stream()
                        .map(c -> {
                            CommentResponse dto = new CommentResponse();
                            dto.setId(c.getId());
                            dto.setMessage(c.getMessage());
                            return dto;
                        })
                        .toList();

        ArticleResponse dto = new ArticleResponse();

        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setContent(article.getContent());

        dto.setUser(userDto);
        dto.setComments(commentsDto);

        return dto;
    }
}