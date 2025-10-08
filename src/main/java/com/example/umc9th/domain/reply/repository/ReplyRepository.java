package com.example.umc9th.domain.reply.repository;

import com.example.umc9th.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByArticleId(
            @Param("articleId") Long articleId
    );
}
