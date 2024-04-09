package com.example.jpaExam.article.tag;

import com.example.jpaExam.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleTagRepository extends JpaRepository<ArticleTag, Integer> {
    void deleteByArticle(Article article);
}
