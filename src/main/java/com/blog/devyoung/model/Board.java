package com.blog.devyoung.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_BOARD")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String language;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime writeDate;
	private int view;

	@PrePersist
	public void createdAt() {
		this.writeDate = LocalDateTime.now();
	}
	
	public String getWriteDate() {
		return writeDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
	}
	
}
