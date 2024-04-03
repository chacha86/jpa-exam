package com.example.jpaExam.article;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속 받아 interface 생성
// <대상엔터티, ID 타입>
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
