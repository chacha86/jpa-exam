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


//        tag1.getArticles().add(article1);
//        tag1.getArticles().add(article2);
//        article1.getTagList().add(tag1); // OK
//        article1.getTagList().add(tag2);
////
//        article2.getTagList().add(tag1);
//        article2.getTagList().add(tag3);
    }

    // 각 게시물의 태그를 출력
    // 각 태그에 해당하는 게시물 출력

}
