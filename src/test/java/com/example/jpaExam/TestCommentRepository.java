package com.example.jpaExam;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCommentRepository extends JpaRepository<TestComment, Integer> {
}
