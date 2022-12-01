package com.zam.rks.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "m_comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne()
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id", referencedColumnName = "id")
	private Post post;

	private String content;
	private Timestamp date;

	public Comment() {
	}

	public Comment(String content, Post post, User user) {
		this.id = -1;
		this.user = user;
		this.post = post;
		this.content = content;
		this.date = new Timestamp(ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).toInstant().toEpochMilli());
	}
}
