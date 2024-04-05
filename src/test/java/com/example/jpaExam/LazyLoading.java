package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleService;
import com.example.jpaExam.member.Member;
import com.example.jpaExam.member.MemberRepository;
import com.example.jpaExam.member.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
public class LazyLoading {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    void t1() {

        List<Member> members = memberRepository.findAll(); // 3명의 회원

        for(Member member : members) {

            System.out.println("========= 회원 ==========");
            System.out.println("회원 이름: " + member.getName());
            System.out.println("========================");

//            System.out.println("========= 작성한 게시물 ==========");
            List<Article> articles = member.getArticles();
            for (Article article : articles) {
//                System.out.println("글 번호: " + article.getId());
//                System.out.println("제목: " + article.getTitle());
//                System.out.println("내용: " + article.getContent());
//                System.out.println("=================================");
            }

        }
        // sql이 필요한 시점까지 실행을 지연. LAZY Loading
    }

    @Autowired
    MemberService memberService;

    @Autowired
    ArticleService articleService;

    @Test
    @Transactional
    @Rollback(false)
    void t2() {
        Member member2 = memberService.save("회원2");
        Article article1 = articleService.save("제목3", "내용3", member2);
        Article article2 = articleService.save("제목4", "내용4", member2);

        Member member3 = memberService.save("회원3");
        Article article3 = articleService.save("제목5", "내용5", member3);
        Article article4 = articleService.save("제목6", "내용6", member3);

    }
}
