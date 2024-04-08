package com.example.jpaExam.article.tag;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag save(String name) {
        Tag tag = new Tag();
        tag.setName(name);

        return tagRepository.save(tag);
    }
}
