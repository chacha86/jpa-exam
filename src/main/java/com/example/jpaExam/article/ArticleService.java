package com.example.jpaExam.article;

import com.example.jpaExam.member.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article save(String title, String content, Member member) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setMember(member);
        article.setCreateDate(LocalDateTime.now());
        return articleRepository.save(article);
    }

    public Article save(String title, String content, Member member, LocalDateTime createDate) {
        Article article = save(title, content, member);
        article.setCreateDate(createDate);
        return articleRepository.save(article);
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public List<CountPerMonth> getCountPerMonth() {
        return articleRepository.getCountPerMonth();
    }
    public Article findById(int id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        articleRepository.deleteById(id);
    }

    public Article update(int id, String title, String content) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        article.setTitle(title);
        article.setContent(content);
        return articleRepository.save(article);
    }

    @Transactional
    public void createTestData() {
        save("제목1", "내용1", null);
        save("제목2", "내용2", null);
        save("제목3", "내용3", null);
    }
}
