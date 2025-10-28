package com.example.umc9th.domain.article.entity;

import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter

public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "like_num")
    private Integer likeNum;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "article")
    private List<Reply> replies = new ArrayList<>();

    public void update(String content) {
        this.content = content;
    }

    public void patch(String content) {
        if (content != null) {
            this.content = content;
        }
    }
}
