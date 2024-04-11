package com.example.jpaExam;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.ArticleRepository;
import com.example.jpaExam.article.MyResultDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BasicTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    @Transactional
    void t1() {
        // 월별 게시물 등록 수

    }

    @Test
    @Transactional
    void t2() {
        List<Article> articles = articleRepository.findByTitleLike("제목");
        for (Article article : articles) {
            System.out.println(article.getTitle());
        }
    }

    @Test
    @Transactional
    void t3() {
//        Object[] result = articleRepository.findTitleAndContent();
//        Object[] result1 = (Object[])result[0];
//
//        System.out.println(result1[0]);
//        System.out.println(result1[1]);
//        System.out.println(result1[2]);

        List<MyResultDto> dtoList = articleRepository.findTitleAndContent();
        for (MyResultDto myResultDto : dtoList) {
            System.out.println(myResultDto.getId());
            System.out.println(myResultDto.getTitle());
            System.out.println(myResultDto.getContent());
        }


//        Object[] result = articleRepository.findTitleAndContent();
//        System.out.println(result[0] instanceof Integer);
//        System.out.println(result[0] instanceof Integer);
//        Integer id = (Integer)result[0];
//        String title = (String)result[1];
//        String content = (String)result[2];

//        System.out.println("id = " + id);
//        System.out.println("title = " + title);
//        System.out.println("content = " + content);
    }

    @Test
    @Transactional
    void t4() {

        Dog d1 = new Dog();
        Cat c1 = new Cat();

        Object[] dogs = {d1, c1};

    }
}

class Animal {
}


class Dog extends Animal{

}

class Cat extends Animal{

}
