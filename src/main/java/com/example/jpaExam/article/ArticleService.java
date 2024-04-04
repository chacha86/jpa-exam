package com.example.jpaExam.article;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article save(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        return articleRepository.save(article);
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
        save("제목1", "내용1");
        save("제목2", "내용2");
        save("제목3", "내용3");
    }
}
