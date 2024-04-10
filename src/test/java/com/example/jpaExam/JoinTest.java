package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleRepository;
import com.example.jpaExam.article.ArticleService;
import com.example.jpaExam.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JoinTest {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void t1() {
        articleRepository.findArticleAndMember();
        articleRepository.findArticleAndMember2();
    }

    @Test
    void t2() {
        List<Article> articleList = articleRepository.findArticleAndMember2();
        for (Article article : articleList) {
            System.out.println("member" + article.getMember().getName());
        }
    }

    @Test
    void t3() {
        List<Article> articleList = articleRepository.findArticleAndMember3();
        for (Article article : articleList) {
            System.out.println("member" + article.getMember().getName());
        }
    }
}
