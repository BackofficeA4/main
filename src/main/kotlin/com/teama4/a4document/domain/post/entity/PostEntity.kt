package com.teama4.a4document.domain.post.entity

import jakarta.persistence.*

@Entity
@Table(name = "post")
class PostEntity(


	@Column(name = "contents")
	var contents: String,

	@Column(name = "title")
	var title: String,

	@Column(name = "created_at")
	var created_at: String,

	) {
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	var id: Long? = null
}
