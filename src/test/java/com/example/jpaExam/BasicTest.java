package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BasicTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    @Transactional
    void t1() {
        // 월별 게시물 등록 수

    }

    @Test
    @Transactional
    void t2() {
        List<Article> articles = articleRepository.findByTitleLike("제목");
        for (Article article : articles) {
            System.out.println(article.getTitle());
        }
    }
}
