package com.example.jpaExam.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(String name) {
        Member member = new Member();
        member.setName(name);
        return memberRepository.save(member);
    }

    public Member getMember(int id) {
        return memberRepository.findById(id).orElse(null);
    }
}
