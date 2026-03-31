package com.TP.JPA;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ArticleSQLRepository extends JpaRepository<ArticleSQL, Long>{
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdNot(String title,Long id);
}
