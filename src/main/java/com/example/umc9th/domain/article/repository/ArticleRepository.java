package com.example.umc9th.domain.article.repository;

import com.example.umc9th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleQueryDSL {
    Slice<Article> findAllByOrderByIdDesc(Pageable pageable);
    Slice<Article> findAllByOrderByLikeNumDescIdDesc(Pageable pageable);

    // 쿼리
    @Query(
            value = """
            SELECT *, CONCAT(LPAD(A.like_num, 10, '0'), LPAD(A.id, 10, '0')) AS cursor_vaule
            FROM article AS A
            WHERE :cursor IS NULL OR CONCAT(LPAD(A.like_num, 10, '0'), LPAD(A.id, 10, '0')) < :cursor
            ORDER BY A.like_num DESC, A.id DESC
            LIMIT 10
            """, nativeQuery = true
    )
    Slice<Article> findSliceByOrderByLikeNum(
            @Param("cursor") String cursor
    );

    // 서비스
    @Query(
            value = """
            SELECT *, LPAD(A.id, 10, '0') AS cursor_vaule
            FROM article AS A
            WHERE LPAD(A.id, 10, '0') < :cursor
            ORDER BY A.like_num DESC, A.id DESC
            LIMIT 10
            """, nativeQuery = true
    )
    Slice<Article> findSliceByOrderById(
            @Param("cursor") String cursor
    );
}
