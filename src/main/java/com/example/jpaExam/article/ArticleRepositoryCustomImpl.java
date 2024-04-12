package com.example.jpaExam.article;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    public List<Article> search() {

        // queryDsl query용 Article 객체
        QArticle article = QArticle.article;

        // select * from article where title like '%aa%' and content like '%bb%'
        List<Article> articleList = jpaQueryFactory
                .select(article)
                .from(article)
                .where(
                        article.title.like("%제목%")
                                .and(article.content.like("%제목%")
                                ))
                .fetch();

        return articleList;
    }

}
