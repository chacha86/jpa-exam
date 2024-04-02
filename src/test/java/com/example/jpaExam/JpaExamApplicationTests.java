package com.example.jpaExam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JpaExamApplicationTests {

    // ArticleRepository를 스프링부트가 알아서 찾아서
    @Autowired
    ArticleRepository articleRepository;

    // 게시물 저장
    @Test
    void contextLoads() {
        Article article = new Article();
        article.setTitle("hihi");
        article.setContent("hihihi");

        articleRepository.save(article); // DB에 반영

        Article article2 = new Article();
        article2.setTitle("제목2");
        article2.setContent("내용2");

        articleRepository.save(article2); // DB에 반영
    }

    // 전체 게시물 가져오기
    @Test
    void test2() {
        List<Article> articleList = articleRepository.findAll();

        for (Article article : articleList) {
            System.out.println(article.getTitle());
        }

    }

    // 아이디를 이용해 게시물 하나만 가져오기
    @Test
    void test3() {
        Article article = articleRepository.findById(1).get();
        System.out.println(article.getTitle());
    }

    @Test
    void Test4() {
        Article article = articleRepository.findById(1).get();

        article.setTitle("제목1");
        articleRepository.save(article);
    }

    // 아이디로 해당 게시물 삭제
    @Test
    void test5() {
        articleRepository.deleteById(1);
    }

    // 객체로 삭제
    @Test
    void test6() {
        Article article = articleRepository.findById(2).get();
        articleRepository.delete(article);
    }
}
