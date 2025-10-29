package com.example.umc9th.domain.reply.entity;

import com.example.umc9th.domain.article.entity.Article;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "reply")
@SQLDelete(sql = "UPDATE reply SET deleted_at = now() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // 연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    // update
    public void updateContent(String content) {
        this.content = content;
    }

    public void updateArticle(Article article) {
        this.article = article;
    }
}
