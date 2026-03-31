package com.TP.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleService {

    @Autowired
    IDAOArticle daoArticle;

    public List<Article> getAllArticles() {
        return daoArticle.getAll();
    }

    public Optional<Article> getArticleById(String id) {
        return daoArticle.findById(id);
    }

    public boolean deleteArticle(String id) {
        return daoArticle.deleteById(id);
    }

    public ArticleServiceResult saveArticle(Article article) {
        boolean isUpdate = article.id != null && !article.id.isEmpty();

        if (isUpdate) {
            Optional<Article> existingArticle = daoArticle.findById(article.id);
            if (existingArticle.isEmpty()) {
                isUpdate = false;
            } else {
                if (daoArticle.existsByTitleAndIdNot(article.title, article.id)) {
                    return new ArticleServiceResult(false, true, null);
                }
            }
        }
        if (!isUpdate) {
            if (daoArticle.existsByTitle(article.title)) {
                return new ArticleServiceResult(false, true, null);
            }
        }
        //save article
        Article savedArticle = daoArticle.save(article);
        return new ArticleServiceResult(isUpdate, false, savedArticle);
    }

    public static class ArticleServiceResult {
        public final boolean isUpdate;
        public final boolean titleExist;
        public final Article article;

        public ArticleServiceResult(boolean isUpdate, boolean titleExist, Article article) {
            this.isUpdate = isUpdate;
            this.titleExist = titleExist;
            this.article = article;
        }
    }
}
