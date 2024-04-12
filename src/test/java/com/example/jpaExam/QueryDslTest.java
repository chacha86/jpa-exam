package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryDslTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    @Transactional
    void t1() {
        List<Article> articleList = articleRepository.search();
    }
}
