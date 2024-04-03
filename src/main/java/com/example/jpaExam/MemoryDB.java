package com.example.jpaExam;

import org.springframework.stereotype.Component;

public class MemoryDB implements MyDB {
    public void run() {
        System.out.println("MemoryDB run");
    }
}
