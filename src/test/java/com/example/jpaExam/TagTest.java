package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleService;
import com.example.jpaExam.article.tag.Tag;
import com.example.jpaExam.article.tag.TagService;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.transaction.Transactional;
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
    @Transactional
    @Rollback(false)
    void t1() {
        Article article1 = articleService.save("제목1", "내용1");
        Article article2 = articleService.save("제목2", "내용2");

        Tag tag1 = tagService.save("놀라움");
        Tag tag2 = tagService.save("공포");
        Tag tag3 = tagService.save("행복");

//        tag1.getArticles().add(article1);
        article1.getTagList().add(tag1); // OK
        article1.getTagList().add(tag2);

        article2.getTagList().add(tag1);
        article2.getTagList().add(tag3);
    }

    // 각 게시물의 태그를 출력
    // 각 태그에 해당하는 게시물 출력

}
