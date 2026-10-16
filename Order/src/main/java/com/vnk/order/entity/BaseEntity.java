package com.vnk.order.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
	
    @CreatedDate
	private LocalDateTime createdBy;
    @LastModifiedDate
	private LocalDateTime updatedBy;
    @CreatedBy
	private Timestamp createdAt;
    @LastModifiedBy
	private Timestamp updatedAt;

}
