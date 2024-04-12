package com.example.jpaExam.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountPerYmDto {
    private String ym; // 연월
    private long cnt; // 게시물 개수
}
