package com.example.jpaExam.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("""
        SELECT m
        FROM Member m
    """)
    List<Member> getMembers();
}
