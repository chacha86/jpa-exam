package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleService;
import com.example.jpaExam.article.tag.Tag;
import com.example.jpaExam.article.tag.TagService;
import com.example.jpaExam.article.tag.Tagging;
import com.example.jpaExam.article.tag.TaggingRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class TagTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;

    @Test
    @DisplayName("Test Data 생성")
    @Transactional
    @Rollback(false)
    void t1() {
        Article article = articleService.save("title1", "content1");
        Tag tag = tagService.save("tag1");

//        article.getTag().add(tag);
//        tag.getArticle().add(article);

    }

    @Autowired
    TaggingRepository taggingRepository;

    @Test
    @DisplayName("tagging으로 태그 구현")
    @Transactional
    @Rollback(false)
    void t2() {
        Article article = articleService.save("title2", "content2");
        Tag tag1 = tagService.save("tag2");

        Tagging tagging = new Tagging();
        tagging.setArticle(article);
        tagging.setTag(tag1);

        taggingRepository.save(tagging);

    }

}
