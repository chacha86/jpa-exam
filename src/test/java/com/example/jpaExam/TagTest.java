package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleService;
import com.example.jpaExam.article.tag.Tag;
import com.example.jpaExam.article.tag.TagService;
import com.example.jpaExam.article.tagging.Tagging;
import com.example.jpaExam.article.tagging.TaggingRepository;
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
        Article article = articleService.save("title1", "content1", null);
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
        Article article = articleService.save("title2", "content2", null);
        Tag tag1 = tagService.save("tag2");

        Tagging tagging = new Tagging();
        tagging.setArticle(article);
        tagging.setTag(tag1);

        taggingRepository.save(tagging);

    }

    @Test
    @DisplayName("Cascade 없이 save - tagging 저장 안됨, article에 cascade 설정하면 추가됨")
    @Transactional
    @Rollback(false)
    void t3() {

        Article article = articleService.save("title3", "content3", null);
        Tag tag1 = tagService.save("tag3");

        Tagging tagging = new Tagging();
        tagging.setArticle(article);
        tagging.setTag(tag1);

        article.getTaggingList().add(tagging);
    }

    @Test
    @DisplayName("Cascade 없이 delete - tagging 삭제 안됨, article에 cascade 설정하면 삭제됨")
    @Transactional
    @Rollback(false)
    void t4() {
        Article article = articleService.findById(3);
        articleService.delete(3);
    }

    @Test
    @DisplayName("ophanRemoval 없이 taggingList 삭제. - test data")
    @Transactional
    @Rollback(false)
    void t5() {
        Article article = articleService.save("title5", "content5", null);
        Tag tag1 = tagService.save("tag5");
        Tag tag2 = tagService.save("tag6");
        Tag tag3 = tagService.findById(1);

        Tagging tagging = new Tagging();
        tagging.setArticle(article);
        tagging.setTag(tag1);

        Tagging tagging2 = new Tagging();
        tagging2.setArticle(article);
        tagging2.setTag(tag2);

        Tagging tagging3 = new Tagging();
        tagging3.setArticle(article);
        tagging3.setTag(tag3);

        article.getTaggingList().add(tagging);
        article.getTaggingList().add(tagging2);
        article.getTaggingList().add(tagging3);
    }


    @Test
    @DisplayName("ophanRemoval 없이 taggingList 삭제.")
    @Transactional
    @Rollback(false)
    void t6() {
        Article article = articleService.findById(4);
        article.getTaggingList().remove(1);
    }

    @Test
    @Transactional
    @Rollback(false)
    void t7() {
        articleService.delete(5);
    }
}
