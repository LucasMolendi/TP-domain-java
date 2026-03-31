package com.TP.JPA;

import com.TP.domain.Article;
import com.TP.domain.IDAOArticle;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DAOArticleSQL implements IDAOArticle {

    private final ArticleSQLRepository repository;
    //getAll
    public DAOArticleSQL(ArticleSQLRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Article> getAll() {
        List<ArticleSQL> articlesSQL = repository.findAll();
        return articlesSQL.stream()
                .map(articleSQL -> {
                    Article article = new Article();
                    article.id = String.valueOf(articleSQL.getId());
                    article.title = articleSQL.getTitle();
                    article.description = articleSQL.getDescription();
                    return article;
                })
                .collect(Collectors.toList());
    }
    //getById
    @Override
    public Optional<Article> findById(String id) {
        return repository.findById(Long.parseLong(id))
                .map(this::toArticle);
    }
    //edit and create
    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }

    @Override
    public boolean existsByTitleAndIdNot(String title, String id) {
        return repository.existsByTitleAndIdNot(title, Long.parseLong(id));
    }

    @Override
    public Article save(Article article) {
        ArticleSQL articleSQL = toArticleSQL(article);
        ArticleSQL saved = repository.save(articleSQL);
        return toArticle(saved);
    }

    //delete
    @Override
    public boolean deleteById(String id) {
        if (repository.existsById(Long.parseLong(id))){
            repository.deleteById(Long.parseLong(id));
            return true;
        }
        return false;
    }

    //SQL convertion
    private Article toArticle(ArticleSQL articleSQL){
        Article article = new Article();
        article.id = String.valueOf(articleSQL.getId());
        article.title = articleSQL.getTitle();
        article.description = articleSQL.getDescription();
        return article;
    }

    private ArticleSQL toArticleSQL(Article article){
        ArticleSQL articleSQL = new ArticleSQL();
        if (article.id != null && !article.id.isEmpty()){
            articleSQL.setId(Long.parseLong(article.id));
        }
        articleSQL.setTitle(article.title);
        articleSQL.setDescription(article.description);
        return articleSQL;
    }
}
