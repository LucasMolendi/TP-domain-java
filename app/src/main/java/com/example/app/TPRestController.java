package com.example.app;

import com.TP.domain.Article;
import com.TP.domain.ArticleService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TPRestController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/articles")
    public ApiResponse getArticles() {
        List<Article> articles = articleService.getAllArticles();
        return new ApiResponse(
                "2002",
                "Tout les articles ont été récupérer avec succès",
                articles
        );
    }

    @GetMapping("/articles/{id}")
    public ApiResponse getArticleById(@PathVariable String id) {
        Optional<Article> article = articleService.getArticleById(id);

        if (article.isPresent()) {
            return new ApiResponse(
                    "2002",
                    "l'article a été récupérer avec succès",
                    null
            );
        }else {
            return new ApiResponse(
                    "7001",
                    "article inconnu",
                    null
            );
        }
    }

    //supprimer via l'ID
    @DeleteMapping("articles/{id}")
    public ResponseEntity<ApiResponse> deleteArticleById(@PathVariable String id) {

        boolean isDeleted = articleService.deleteArticle(id);

        if (isDeleted) {
            ApiResponse response = new ApiResponse(
                    "2002",
                    "article supprimé avec succès",
                    true
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(
                    "7001",
                    "article inconnu",
                    false
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // save / create / update
    @PostMapping("articles")
    public ResponseEntity<ApiResponse> createArticle(@RequestBody Article article) {

        article.id = null;

        ArticleService.ArticleServiceResult result = articleService.saveArticle(article);

        if (result.titleExist) {
            ApiResponse response = new ApiResponse(
                    "7006",
                    "Titre déjà utilisé",
                    null
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        ApiResponse response = new ApiResponse(
                "2002",
                "Article crée avec succès, Félicitations !!",
                result.article
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("articles/{id}")
    public ResponseEntity<ApiResponse> updateArticle(@PathVariable String id, @Nonnull @RequestBody Article article) {
        article.id = id;

        ArticleService.ArticleServiceResult result = articleService.saveArticle(article);

        if (result.titleExist) {
            ApiResponse response = new ApiResponse(
                    "7006",
                    "Titre déjà utilisé",
                    null
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        ApiResponse response = new ApiResponse(
                "2003",
                "Article modifié avec succès",
                result.article
        );
        return ResponseEntity.ok(response);
    }
}
