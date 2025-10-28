package com.example.umc9th.domain.reply.repository;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    //게시글 댓글 전체 조회
    List<Reply> findAllByArticle(Article article);
}
