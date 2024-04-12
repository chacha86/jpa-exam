package com.example.jpaExam.article;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    public List<Article> search(List<String> types, String kw) {

        // queryDsl query용 Article 객체
        QArticle article = QArticle.article;

        List<BooleanExpression> conditions = new ArrayList<>();

        for (String type : types) {
            if (type.equals("title")) {
                conditions.add(article.title.like("%" + kw + "%"));
            }

            if (type.equals("content")) {
                conditions.add(article.content.like("%" + kw + "%"));
            }

            if (type.equals("memberName")) {
                conditions.add(article.member.name.like("%" + kw + "%"));
            }
        }

        BooleanBuilder builder = new BooleanBuilder();

        for(BooleanExpression condition : conditions) {
            builder.or(condition);
        }
        // select * from article where title like '%aa%' and content like '%bb%'
        List<Article> articleList = jpaQueryFactory
                .select(article)
                .from(article)
                .where(builder)
                .fetch();

        return articleList;
    }

}
