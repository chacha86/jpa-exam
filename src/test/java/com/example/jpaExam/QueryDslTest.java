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
//        List<Article> articleList = articleRepository.search();
    }

    // 다양한 조건 검색
    @Test
    @Transactional
    void t2() {

        // 제목, 내용, 작성자로 검색
        String kw = "제목";
        List<String> types = List.of("content", "memberName");

        // 제목, 내용 검색
//        List<String> types2 = List.of("content", "title");

        List<Article> searchedList = articleRepository.search(types, kw);

    }
}
