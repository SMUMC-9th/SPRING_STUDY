package com.example.umc9th.domain.article.entity;

import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "like_num")
    private int likeNum;

    @Builder.Default
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();

    public void updateArticle(String title, String content) {
        if (title != null) this.title = title;
        if (content != null) this.content = content;
    }
}
