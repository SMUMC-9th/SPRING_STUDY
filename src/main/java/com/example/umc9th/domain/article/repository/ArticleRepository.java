package com.example.umc9th.domain.article.repository;

import com.example.umc9th.domain.article.entity.Article;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = """
        SELECT a.*,
               CONCAT(LPAD(a.like_num, 10, '0'), LPAD(a.id, 10, '0')) AS cursor_value
        FROM article AS a
        WHERE (:cursor IS NULL
                OR CONCAT(LPAD(a.like_num, 10, '0'), LPAD(a.id, 10, '0')) < :cursor)
        ORDER BY a.like_num DESC, a.id DESC
        LIMIT :limit
        """, nativeQuery = true)
    Slice<Article> findByCursor(
            @Param("cursor") String cursor,
            @Param("limit") int limit
    );
}
