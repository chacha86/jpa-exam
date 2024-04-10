package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleRepository;
import com.example.jpaExam.article.ArticleService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToStringTest {

    @Autowired
    ArticleRepository articleRepository;
    @Test
    @Transactional
    void t1() {
        Article article = articleRepository.findById(1).get();
        System.out.println(article);
    }
}
