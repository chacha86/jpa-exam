package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleService;
import com.example.jpaExam.article.CountPerMonth;
import com.example.jpaExam.article.tag.Tag;
import com.example.jpaExam.article.tag.TagService;
import com.example.jpaExam.article.tagging.Tagging;
import com.example.jpaExam.article.tagging.TaggingService;
import com.example.jpaExam.member.Member;
import com.example.jpaExam.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class GroupingTest {
    @Autowired
    ArticleService articleService;

    @Autowired
    MemberService memberService;

    @Autowired
    TaggingService taggingService;

    @Autowired
    TagService tagService;

    @Test
    @DisplayName("test data insert")
    void t1() {
        Random random = new Random();
        List<String> titlePrefixList = List.of("바나나", "딸기", "오렌지", "블루베리");
        List<String> tagList = List.of("맛있는", "쉬원한", "달콤한", "상큼한", "신선한", "비타민C가 풍부한");
        List<String> memberList = List.of(
                "홍길동",
                "김길수",
                "홍영희",
                "박영수"
        );

        for (int i = 0; i < 300; i++) {
            int titleIdx = random.nextInt(titlePrefixList.size());
            int tagIdx = random.nextInt(tagList.size());
            int memberIdx = random.nextInt(memberList.size());
            int dateIdx = random.nextInt(12);

            String name = memberList.get(memberIdx);
            Member member = memberService.save(name);

            String title = titlePrefixList.get(titleIdx) + "_" + i;
            LocalDateTime createDate = LocalDateTime.now().minusMonths(dateIdx);
            Article article = articleService.save(title, title, member, createDate);

            Tag tag = tagService.save(tagList.get(tagIdx));

            taggingService.save(article, tag);

        }
    }

    @Test
    @DisplayName("년월별 게시글 수")
    void t2() {
        List<CountPerMonth> countPerMonthList = articleService.getCountPerMonth();
        for (CountPerMonth countPerMonth : countPerMonthList) {
            System.out.println(countPerMonth.getYm() + " : " + countPerMonth.getCount());
        }
    }

}
