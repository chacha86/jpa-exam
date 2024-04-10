package com.example.jpaExam.article.tagging;

import com.example.jpaExam.article.Article;
import com.example.jpaExam.article.tag.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaggingService {

    private final TaggingRepository taggingRepository;

    public Tagging save(Article article, Tag tag) {
        Tagging tagging = new Tagging();
        tagging.setArticle(article);
        tagging.setTag(tag);
        return taggingRepository.save(tagging);
    }

    public Tagging getTagging(int id) {
        return taggingRepository.findById(id).orElse(null);
    }
}
