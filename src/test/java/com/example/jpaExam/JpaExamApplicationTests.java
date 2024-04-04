package com.example.jpaExam;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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


    @Autowired
    TestArticleRepository testArticleRepository;
    @Autowired
    TestMemberRepository testMemberRepository;
    @Test
    @DisplayName("단방향 설정")
    @Transactional
    @Rollback(false)
    void t14() {
        TestMember testMember = new TestMember();
        testMember.setUsername("kim");

        testMemberRepository.save(testMember);

        TestArticle testArticle = new TestArticle();
        testArticle.setTitle("제목");
        testArticle.setBody("내용");

        testArticle.setTestMember(testMember);

        testArticleRepository.save(testArticle);
    }

    @Autowired
    TestCommentRepository testCommentRepository;
    @Test
    @DisplayName("양방향 설정 = mappedBy 없이, 객체 연결 없이. 외래키 null. article_comments 테이블에 데이터 없음")
    @Transactional
    @Rollback(false)
    void t15() {
        TestArticle testArticle = new TestArticle();
        testArticle.setTitle("제목2");
        testArticle.setBody("내용2");

        TestComment testComment = new TestComment();
        testComment.setComment("댓글1");

        TestComment testComment2 = new TestComment();
        testComment2.setComment("댓글2");

        testArticleRepository.save(testArticle);
        testCommentRepository.save(testComment);
        testCommentRepository.save(testComment2);

    }

    // mappedBy 없이, 객체 한쪽만(Article) 연결 - 외래키 null. article_comments 테이블에 데이터 생성
    // 데이터가 이원화 되고 관리가 어려워짐.
    @Test
    @DisplayName("양방향 설정 = mappedBy 없이, 객체 한쪽만(Article) 연결 - 외래키 null. article_comments 테이블에 데이터 생성")
    @Transactional
    @Rollback(false)
    void t16() {
        TestArticle testArticle = new TestArticle();
        testArticle.setTitle("제목2");
        testArticle.setBody("내용2");

        TestComment testComment = new TestComment();
        testComment.setComment("댓글1");

        TestComment testComment2 = new TestComment();
        testComment2.setComment("댓글2");

        testArticleRepository.save(testArticle);
        testCommentRepository.save(testComment);
        testCommentRepository.save(testComment2);

        testArticle.getComments().add(testComment);
        testArticle.getComments().add(testComment2);

        testArticleRepository.save(testArticle);


    }

    @Test
    @DisplayName("양방향 설정 = mappedBy 없이, 객체 한쪽만(comment) 연결 - 위와 결과 동일")
    @Transactional
    @Rollback(false)
    void t17() {
        TestArticle testArticle = new TestArticle();
        testArticle.setTitle("제목2");
        testArticle.setBody("내용2");

        TestComment testComment = new TestComment();
        testComment.setComment("댓글1");

        testComment.setTestArticle(testArticle);

        TestComment testComment2 = new TestComment();
        testComment2.setComment("댓글2");
        testComment2.setTestArticle(testArticle);

        testArticleRepository.save(testArticle);
        testCommentRepository.save(testComment);
        testCommentRepository.save(testComment2);
    }

    @Test
    @DisplayName("양방향 설정 = mappedBy 있고, 객체 한쪽만(comment) 연결. - article_comments 테이블에 데이터 생성되지 않음. 외래키 잘 들어감")
    @Transactional
    @Rollback(false)
    void t18() {
        TestArticle testArticle = new TestArticle();
        testArticle.setTitle("제목2");
        testArticle.setBody("내용2");

        TestComment testComment = new TestComment();
        testComment.setComment("댓글1");

        testComment.setTestArticle(testArticle);

        TestComment testComment2 = new TestComment();
        testComment2.setComment("댓글2");
        testComment2.setTestArticle(testArticle);

        testArticleRepository.save(testArticle);
        testCommentRepository.save(testComment);
        testCommentRepository.save(testComment2);
    }

    @Test
    @DisplayName("양방향 설정 = mappedBy 있고, 객체 한쪽만(article) 연결. - article_comments 테이블에 데이터 생성되지 않음. 외래키 null")
    @Transactional
    @Rollback(false)
    void t19() {
        TestArticle testArticle = new TestArticle();
        testArticle.setTitle("제목2");
        testArticle.setBody("내용2");

        TestComment testComment = new TestComment();
        testComment.setComment("댓글1");

        TestComment testComment2 = new TestComment();
        testComment2.setComment("댓글2");

        testArticle.getComments().add(testComment);
        testArticle.getComments().add(testComment2);

        testArticleRepository.save(testArticle);
        testCommentRepository.save(testComment);
        testCommentRepository.save(testComment2);
    }

    @Test
    @DisplayName("양방향 설정 = mappedBy 있고, 객체 한쪽(comment) 연결하고 list 다루기 - 트랜잭션 내에서 list 동기화가 안됨.")
    @Transactional
    @Rollback(false)
    void t20() {
        TestArticle testArticle = testArticleRepository.findById(1).get();

        TestComment testComment = new TestComment();
        testComment.setComment("댓글3");
        testComment.setTestArticle(testArticle);

        for (TestComment comment : testArticle.getComments()) {
            System.out.println(comment.getComment());
        }

        testCommentRepository.save(testComment);

    }
    @Test
    @DisplayName("양방향 설정 = mappedBy 있고, 객체 양쪽 연결하고 list 다루기 - 트랜잭션 내에서 list 동기화가 안됨.")
    @Transactional
    @Rollback(false)
    void t21() {
        TestArticle testArticle = testArticleRepository.findById(1).get();

        TestComment testComment = new TestComment();
        testComment.setComment("댓글3");
        testComment.setTestArticle(testArticle);

        testArticle.getComments().add(testComment);

        for (TestComment comment : testArticle.getComments()) {
            System.out.println(comment.getComment());
        }

        testCommentRepository.save(testComment);

    }
}
