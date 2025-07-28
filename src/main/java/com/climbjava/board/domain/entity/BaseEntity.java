package com.climbjava.board.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(exclude = "modDate")
public class BaseEntity {
  @CreatedDate
  @Column(name = "regdate", updatable = false)
  private LocalDateTime regDate;

  @LastModifiedDate
  @Column(name = "moddate")
  private LocalDateTime modDate;
}
