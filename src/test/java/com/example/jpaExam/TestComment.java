package com.example.jpaExam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TestComment {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String comment;

    @ManyToOne
    private TestArticle testArticle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TestArticle getTestArticle() {
        return testArticle;
    }

    public void setTestArticle(TestArticle testArticle) {
        this.testArticle = testArticle;
    }
}
