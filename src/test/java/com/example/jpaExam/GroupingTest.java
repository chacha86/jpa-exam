package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootTest
public class GroupingTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    void t1() {
        Random random = new Random();

        for(int i = 0; i < 100; i++) {

            int randInt = random.nextInt(12);

            Article article = new Article();
            article.setTitle("제목" + i);
            article.setContent("내용" + i);
            article.setCreateDate(LocalDateTime.now().minusMonths(randInt));

            articleRepository.save(article);
        }
    }

    @Test
    void t2() {
        // 월별 게시물 등록 수

    }
}
