package com.example.jpaExam.article;

import com.example.jpaExam.article.tag.ArticleTag;
import com.example.jpaExam.article.tag.Tag;
import com.example.jpaExam.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @ManyToMany // 실제로는 이렇게 안함.
//    @JoinTable(
//            name = "article_tag",
//            joinColumns = @JoinColumn(name = "article_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private List<Tag> tagList = new ArrayList<>();

    @OneToMany(mappedBy = "article", orphanRemoval = true, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<ArticleTag> articleTags = new ArrayList<>();

    private LocalDateTime createDate;
}
