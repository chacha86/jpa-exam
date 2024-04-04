package com.example.jpaExam;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestMemberRepository extends JpaRepository<TestMember, Integer> {
}
