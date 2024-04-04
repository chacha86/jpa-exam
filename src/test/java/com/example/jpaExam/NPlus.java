package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleService;
import com.example.jpaExam.comment.Comment;
import com.example.jpaExam.comment.CommentService;
import com.example.jpaExam.member.Member;
import com.example.jpaExam.member.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
public class NPlus {

    @Autowired
    ArticleService articleService;

    @Autowired
    MemberService memberService;

    // 테스트 데이터 함수
    @Test
    @Transactional
    @Rollback(value = false)
    void t1() {
        // 회원 1명 저장
        Member member1 = memberService.save("회원1");
        Member member2 = memberService.save("회원2");

        // 회원1의 게시물 3개 저장
        Article article1 = articleService.save("제목1", "내용1");
        Article article2 = articleService.save("제목2", "내용2");
        Article article3 = articleService.save("제목3", "내용3");

        member1.addArticle(article1);
        member1.addArticle(article2);
        member1.addArticle(article3);

        // 회원2의 게시물 2개 저장
        Article article4 = articleService.save("제목4", "내용4");
        Article article5 = articleService.save("제목5", "내용5");

        member2.addArticle(article4);
        member2.addArticle(article5);

    }

    // 게시물의 개수만큼 N+1 문제 발생
    @Test
    @DisplayName("@OneToMany N+1 문제 발생시키기. @OneToMany는 기본적으로 LAZY이므로 EAGER로 바꿈.")
    @Transactional
    void t2() {
        List<Member> members = memberService.findAll();
    }

    @Autowired
    CommentService commentService;
    @Test
    @DisplayName("Comment Test data 생성")
    @Transactional
    @Rollback(value = false)
    void t3() {
        Article article = articleService.findById(1);

        Comment comment = commentService.save("댓글1");
        Comment comment2 = commentService.save("댓글2");
        Comment comment3 = commentService.save("댓글3");

        comment.setArticle(article);
        comment2.setArticle(article);
        comment3.setArticle(article);

        Article article2 = articleService.findById(2);

        Comment comment4 = commentService.save("댓글4");
        Comment comment5 = commentService.save("댓글5");

        comment4.setArticle(article2);
        comment5.setArticle(article2);

        Comment comment6 = commentService.save("댓글6");
        Comment comment7 = commentService.save("댓글7");

        Article article3 = articleService.findById(3);

        comment6.setArticle(article3);
        comment7.setArticle(article3);


    }
    // 댓글의 게시물 그룹 수 만큼 N+1 문제 발생
    @Test
    @DisplayName("@ManyToOne N+1 문제 발생시키기. @ManyToOne은 기본적으로 EAGER")
    @Transactional
    void t4() {
        List<Comment> comments = commentService.findAll();
    }


}
