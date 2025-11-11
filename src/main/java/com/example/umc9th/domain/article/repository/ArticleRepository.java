package com.example.umc9th.domain.article.repository;

import com.example.umc9th.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
// JpaRepository의 첫 번째는 해당 Repository가 사용할 클래스(엔티티)가 들어가야합니다.
// 두 번째는 id의 자료형을 적어줍니다.

    // 좋아요 수 기준 Cursor Pagination (id와 concat)
    @Query(value = """
            SELECT *
            FROM article a
            WHERE CONCAT(LPAD(a.like_num, 10, '0'), LPAD(a.id, 10, '0')) < :cursor 
            ORDER BY a.like_num DESC, a.id DESC
            LIMIT :limit
            """, nativeQuery = true) // nativeQuery = true, 순수 sql
    List<Article> findArticlesByLikeNumCursor(
            @Param("cursor") String cursor,
            @Param("limit") int limit
    );
}
