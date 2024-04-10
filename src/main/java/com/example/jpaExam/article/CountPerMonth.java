package com.example.jpaExam.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountPerMonth {
    private String ym;
    private long count;
}
