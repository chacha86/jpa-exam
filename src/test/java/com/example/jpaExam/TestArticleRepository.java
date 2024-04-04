package com.example.jpaExam;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestArticleRepository extends JpaRepository<TestArticle, Integer> {
}
