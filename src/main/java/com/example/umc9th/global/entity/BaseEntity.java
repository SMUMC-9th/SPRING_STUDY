package com.example.umc9th.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //엔티티의 공통 속성(superclass) 로 사용하겠다고 선언
@EntityListeners(AuditingEntityListener.class)
/*엔티티의 상태 변화를 감지하고 자동으로 처리하도록 리스너를 등록엔티티 생성/수정 시 @CreatedDate, @LastModifiedDate 필드가 자동 업데이트*/
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

}
