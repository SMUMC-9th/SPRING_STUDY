package com.example.umc9th.domain.reply.repository;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    //게시글 댓글 전체 조회
    List<Reply> findAllByArticle(Article article);

    // 게시글에 댓글이 존재하는지 여부 확인
    boolean existsByArticle_Id(Long articleId); //_써도되고 아니어도됨

    //댓글 offset기반 페이지네이션
    Page<Reply> findByArticle_IdOrderByCreatedAtDesc(Long articleId, Pageable pageable);

}
