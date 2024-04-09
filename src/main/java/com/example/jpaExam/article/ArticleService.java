package com.example.jpaExam.article;

import com.example.jpaExam.member.Member;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 서비스 클래스
// 트랜잭션 처리 및 관리
// 트랜잭션 - 업무 로직과 관련 => 집중화
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public Article save(String title, String content, Member member) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setMember(member);

        return articleRepository.save(article);
    }

    public Article save(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);

        return articleRepository.save(article);
    }

    public Article findById(int id) {
        return articleRepository.findById(id).get();
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }
}
