package com.example.umc9th.domain.article.repository;

import com.example.umc9th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);

    Slice<Article> findAllByOrderByIdDesc(Pageable pageable);

    @Query("""
        SELECT a 
        FROM Article a 
        WHERE CONCAT(FUNCTION('DATE_FORMAT', a.createdAt, '%Y%m%d%H%i%s'), '_', a.id) < :cursor
        ORDER BY a.createdAt DESC, a.id DESC
        """)
    Slice<Article> findAllByCreatedAtCursor(@Param("cursor") String cursor, Pageable pageable);

    @Query("""
        SELECT a 
        FROM Article a 
        ORDER BY a.createdAt DESC, a.id DESC
        """)
    Slice<Article> findAllByOrderByCreatedAtDescWithId(Pageable pageable);

    @Query("""
        SELECT a 
        FROM Article a 
        WHERE CONCAT(LPAD(CAST(a.likeNum AS string), 10, '0'), '_', a.id) < :cursor 
        ORDER BY a.likeNum DESC, a.id DESC""")
    Slice<Article> findAllByLikeCursor(@Param("cursor") String cursor, Pageable pageable);

    @Query("""
        SELECT a 
        FROM Article a 
        ORDER BY a.likeNum DESC, a.id DESC
        """)
    Slice<Article> findAllByLikeNumDesc(Pageable pageable);

    @Query("""
        SELECT a 
        FROM Article a 
        WHERE a.title 
        LIKE %:keyword%
        ORDER BY a.createdAt DESC, a.id DESC
        """)
    Slice<Article> searchByTitleWithCursor(@Param("keyword") String keyword, Pageable pageable);

}