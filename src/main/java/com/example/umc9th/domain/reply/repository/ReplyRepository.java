package com.example.umc9th.domain.reply.repository;

import com.example.umc9th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByArticleId(Long articleId);


    // 게시글에 댓글이 있는지 확인
    boolean existsByArticleId(Long articleId);

    //Offset 기반 페이지네이션
    Page<Reply> findAllByArticleIdOrderByCreatedAtDesc(Long articleId, Pageable pageable);
}