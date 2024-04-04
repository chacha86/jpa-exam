package com.example.jpaExam.article.tag;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.tag.Tag;
import com.example.jpaExam.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tagging {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Tag tag;

    private LocalDateTime createdAt;
}
