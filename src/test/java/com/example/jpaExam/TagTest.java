package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleService;
import com.example.jpaExam.article.tag.ArticleTag;
import com.example.jpaExam.article.tag.ArticleTagRepository;
import com.example.jpaExam.article.tag.Tag;
import com.example.jpaExam.article.tag.TagService;
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

    @Autowired
    ArticleTagRepository articleTagRepository;

    @Test
    @Transactional
    @Rollback(false)
    void t1() {
        Article article1 = articleService.save("제목1", "내용1");
        Article article2 = articleService.save("제목2", "내용2");

        Tag tag1 = tagService.save("놀라움");
        Tag tag2 = tagService.save("공포");
        Tag tag3 = tagService.save("행복");

        // 1번 게시물에 1번 태그 추가
        ArticleTag articleTag1 = new ArticleTag();
        articleTag1.setArticle(article1);
        articleTag1.setTag(tag1);

        // 1번 게시물에 2번 태그 추가
        ArticleTag articleTag2 = new ArticleTag();
        articleTag2.setArticle(article1);
        articleTag2.setTag(tag2);

        // 2번 게시물에 1번 태그 추가
        ArticleTag articleTag3 = new ArticleTag();
        articleTag3.setArticle(article2);
        articleTag3.setTag(tag1);

        // 2번 게시물에 3번 태그 추가
        ArticleTag articleTag4 = new ArticleTag();
        articleTag4.setArticle(article2);
        articleTag4.setTag(tag3);

        articleTagRepository.save(articleTag1);
        articleTagRepository.save(articleTag2);
        articleTagRepository.save(articleTag3);
        articleTagRepository.save(articleTag4);
    }

    @Test
    @Transactional
    @Rollback(false)
    void t2() {
        // 추가
        Article article1 = articleService.findById(1);
        Tag tag1 = tagService.findById(3);

        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticle(article1);
        articleTag.setTag(tag1);

        article1.getArticleTags().add(articleTag);

    }


    @Test
    @Transactional
    @Rollback(false)
    void t3() {
        // 삭제

        // 외래키로 참조되고 있는 객체는 먼저 지울 수 없다.
        // 참조하고 있는 객체를 먼저 지우고 참조되는 객체는 나중에 지운다.
        // 부모(1) - 자식(N) 관계에서는 자식이 먼저 삭제되어야 한다.

        Article article1 = articleService.findById(1);
        articleService.delete(article1); // 부모 삭제

    }
}
