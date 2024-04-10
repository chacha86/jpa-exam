package com.example.jpaExam.article;

import com.example.jpaExam.article.tagging.Tagging;
import com.example.jpaExam.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Member member;

    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST)
    private List<Tagging> taggingList = new ArrayList<>();

    private LocalDateTime createDate;
}
