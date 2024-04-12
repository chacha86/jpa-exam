package com.example.jpaExam.article;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<Article> search(List<String> types, String kw);
}
