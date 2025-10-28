package com.example.umc9th.domain.article.entity;

import com.example.umc9th.domain.reply.entity.Reply;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "article")
@Builder
@Getter
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    ArrayList<Reply> replies = new ArrayList<>();

    public void addReply(Reply reply){
        replies.add(reply);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
