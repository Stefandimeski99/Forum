package com.example.project.Repository;

import com.example.project.Model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByTopicLike(String topic);
    List<Article> findAllByTitleLike(String title);
}
