package com.example.jpaExam;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TestArticle {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name="member_id")
    private TestMember testMember;

    @OneToMany(mappedBy = "testArticle")
    private List<TestComment> comments = new ArrayList<>();

    public TestArticle() {
    }

    public TestArticle(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public TestMember getTestMember() {
        return testMember;
    }

    public void setTestMember(TestMember testMember) {
        this.testMember = testMember;
    }

    public List<TestComment> getComments() {
        return comments;
    }

    public void setComments(List<TestComment> comments) {
        this.comments = comments;
    }
}
