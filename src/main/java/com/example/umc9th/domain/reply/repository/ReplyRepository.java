package com.example.umc9th.domain.reply.repository;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    boolean existsByArticle(Article article);

    Page<Reply> findAllByArticleOrderByCreatedAtDesc (Article article, Pageable pageable);
}
