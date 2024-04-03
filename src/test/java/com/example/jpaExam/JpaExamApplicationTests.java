package com.example.jpaExam;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JpaExamApplicationTests {

    // ArticleRepository를 스프링부트가 알아서 찾아서
    @Autowired
    ArticleRepository articleRepository;

    // 게시물 저장
    @Test
    void contextLoads() {
        Article article = new Article();
        article.setTitle("hihi");
        article.setContent("hihihi");

        articleRepository.save(article); // DB에 반영

        Article article2 = new Article();
        article2.setTitle("제목2");
        article2.setContent("내용2");

        articleRepository.save(article2); // DB에 반영
    }

    // 전체 게시물 가져오기
    @Test
    void test2() {
        List<Article> articleList = articleRepository.findAll();

        for (Article article : articleList) {
            System.out.println(article.getTitle());
        }

    }

    // 아이디를 이용해 게시물 하나만 가져오기
    @Test
    void test3() {
        Article article = articleRepository.findById(1).get();
        System.out.println(article.getTitle());
    }

    @Test
    void Test4() {
        Article article = articleRepository.findById(1).get();

        article.setTitle("제목1");
        articleRepository.save(article);
    }

    // 아이디로 해당 게시물 삭제
    @Test
    void test5() {
        articleRepository.deleteById(1);
    }

    // 객체로 삭제
    @Test
    void test6() {
        Article article = articleRepository.findById(2).get();
        articleRepository.delete(article);
    }

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("Transaction 테스트1 - 데이터 생성")
    void t11() {
        Account account = new Account();
        account.setUsername("kim");
        account.setAmount(10000);


        Account account2 = new Account();
        account2.setUsername("hong");
        account2.setAmount(10000);

        accountRepository.save(account);
        accountRepository.save(account2);

    }

    @Test
    @DisplayName("Transaction 테스트2 - 트랜잭션 없이 진행")
    void t12() {
        Account account = accountRepository.findById(1L).get();
        account.setAmount(account.getAmount() - 1000);

        accountRepository.save(account);

        if(true)
            throw new RuntimeException();

        Account account2 = accountRepository.findById(2L).get();
        account2.setAmount(account2.getAmount() + 1000);

        accountRepository.save(account2);
    }

    @Test
    @DisplayName("Transaction 테스트3 - 트랜잭션 체크 후 진행")
    @Transactional
    void t13() {
        Account account = accountRepository.findById(1L).get();
        account.setAmount(account.getAmount() - 1000);

        accountRepository.save(account);

        if(true)
            throw new RuntimeException();

        Account account2 = accountRepository.findById(2L).get();
        account2.setAmount(account2.getAmount() + 1000);

        accountRepository.save(account2);
    }

    @Test
    @DisplayName("영속성 컨텍스트1 - 1차 캐시 존재 확인")
    @Transactional
    @Rollback(false)
    void t9() {
        Article article = articleRepository.findById(3).get();
        System.out.println(article.getTitle());

        Article article2 = articleRepository.findById(3).get();
        System.out.println(article2.getTitle());
    }

    @Test
    @DisplayName("영속성 컨텍스트 확인2 - insert 하면 영속성 컨텍트스에 저장됨")
    @Transactional
    @Rollback(false)
    void t10() {
        Article article = new Article();
        article.setTitle("제목3");
        article.setContent("내용3");

        articleRepository.save(article);

        int id = article.getId();
        System.out.println("id = " + id);

        Article target = articleRepository.findById(id).get(); // select 쿼리가 안나감
        System.out.println(target.getTitle());
    }

    @Test
    @Transactional
    @Rollback(false)
    @DisplayName("영속성 컨텍스트 확인3 - dirty checking")
    void t7() {
        Article article = articleRepository.findById(3).get();

        article.setTitle("제목123123");
    }

    @Test
    @DisplayName("영속성 컨텍스트 확인4 - sql 쓰기 지연 - delete가 한번에 처리됨")
    @Rollback(false)
    @Transactional
    void t8() {
        Article article1 = new Article();
        article1.setTitle("제목4");
        article1.setContent("내용4");

        Article article2 = new Article();
        article2.setTitle("제목4");
        article2.setContent("내용4");

        articleRepository.save(article1);
        articleRepository.save(article2);

        System.out.println("========== article1 삭제 =========");
        articleRepository.delete(article1);
        System.out.println("==================================");

        System.out.println("========== article2 삭제 =========");
        articleRepository.delete(article2);
        System.out.println("==================================");

    }
}
