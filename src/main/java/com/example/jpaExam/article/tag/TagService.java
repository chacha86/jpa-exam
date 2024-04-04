package com.example.jpaExam.article.tag;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag save(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagRepository.save(tag);
    }

    public Tag findById(int id) {
        return tagRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        tagRepository.deleteById(id);
    }

    public Tag update(int id, String name) {
        Tag tag = tagRepository.findById(id).orElse(null);
        if (tag == null) {
            return null;
        }
        tag.setName(name);
        return tagRepository.save(tag);
    }


    @Transactional
    public void createTestData() {
        save("태그1");
        save("태그2");
        save("태그3");

    }
}
