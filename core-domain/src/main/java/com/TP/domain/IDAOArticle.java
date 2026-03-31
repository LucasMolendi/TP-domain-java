package com.TP.domain;

import java.util.List;
import java.util.Optional;

public interface IDAOArticle {
    //récupérer tout les articles
    public List<Article> getAll();

    //récupération par ID
    Optional<Article> findById(String id);

    //suppresion d'un article
    boolean deleteById(String id);

    //save / update / create
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdNot(String title, String id);
    Article save(Article article);
}
