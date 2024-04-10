package com.example.jpaExam.member;

import com.example.jpaExam.article.Article;
import jakarta.persistence.*;
import lombok.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String name;

    // mappedBy 속성은 연관관계의 주인이 아닌 쪽에서 사용한다.
    // 관계의 주인??? -> 외래키를 가지고 있는 애가 주인(N쪽)
    // 양방향 관계에서는 관계의 주인에 적용된 것만 DB에 반영된다.
    @OneToMany(mappedBy = "member")
    List<Article> articles = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }

}
