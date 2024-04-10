package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleRepository;
import com.example.jpaExam.article.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SearchTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    void t1() {
        List<Article> articles = articleRepository.findArticleByTitle("바나나");

        for (Article article : articles) {
            System.out.println(article.getTitle());
        }
    }
}
