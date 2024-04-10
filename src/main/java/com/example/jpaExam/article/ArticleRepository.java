package com.example.jpaExam.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JpaRepository 상속 받아 interface 생성
// <대상엔터티, ID 타입>
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("""
        SELECT new com.example.jpaExam.article.CountPerMonth(
            concat(year(a.createDate), '-', month(a.createDate)), count(a)
        )
        FROM Article a
        group by concat(year(a.createDate), '-', month(a.createDate))
        order by concat(year(a.createDate), '-', month(a.createDate)) desc
    """)
    List<CountPerMonth> getCountPerMonth();

    @Query("""
        SELECT a, a.member
        FROM Article a
    """)
    List<Article> findArticleAndMember();

    // member와 조인해서 member에 접근할 수 있지만 결과를 Article만 영속화하고 Member는 영속화하지 않음
    @Query("""
        SELECT a
        FROM Article a
        JOIN a.member m
    """)
    List<Article> findArticleAndMember2();

    // fetch join -> member를 미리 조인해서 가져와 영속화
    @Query("""
        SELECT a
        FROM Article a
        JOIN FETCH a.member m
    """)
    List<Article> findArticleAndMember3();


    // 파라미터 바인딩 및 기본 검색
    @Query("""
        SELECT a
        FROM Article a
        WHERE a.title LIKE concat('%', :title, '%')
    """)
    List<Article> findArticleByTitle(@Param("title") String title);

    
}
