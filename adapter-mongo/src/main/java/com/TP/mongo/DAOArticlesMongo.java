package com.TP.mongo;

import com.TP.domain.IDAOArticle;
import com.TP.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DAOArticlesMongo implements IDAOArticle {
    @Autowired
    ArticleMongoRepository articleMongoRepository;

    //getAll
    @Override
    public List<Article> getAll() {
        List<ArticleMongo> articlesMongo = articleMongoRepository.findAll();
        return articlesMongo.stream()
                .map(articleMongo -> {
                    Article article = new Article();
                    article.id = articleMongo.id;
                    article.title = articleMongo.title;
                    article.description = articleMongo.description;
                    return article;
                })
                .collect(Collectors.toList());
    }

    //getbyId
    @Override
    public Optional<Article> findById(String id) {
        return articleMongoRepository.findById(id)
                .map(articleMongo -> {
                    Article article = new Article();
                    article.id = articleMongo.id;
                    article.title = articleMongo.title;
                    article.description = articleMongo.description;
                    return article;
                });
    }

    //update, edit and create
    @Override
    public boolean existsByTitle(String title) {
        return articleMongoRepository.existsByTitle(title);
    }
    @Override
    public boolean existsByTitleAndIdNot(String title, String id) {
        return articleMongoRepository.existsByTitleAndIdNot(title, id);
    }

    @Override
    public Article save(Article article) {
        ArticleMongo articleMongo = new ArticleMongo();
        articleMongo.id = article.id;
        articleMongo.title = article.title;
        articleMongo.description = article.description;

        ArticleMongo saved = articleMongoRepository.save(articleMongo);

        Article result = new Article();
        result.id = saved.id;
        result.title = saved.title;
        result.description = saved.description;

        return result;
    }
    //delete
    @Override
    public boolean deleteById(String id) {
        if (articleMongoRepository.existsById(id)) {
            articleMongoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
