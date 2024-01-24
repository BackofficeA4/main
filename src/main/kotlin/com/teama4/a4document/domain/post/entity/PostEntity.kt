package com.teama4.a4document.domain.post.entity

import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.domain.dto.PostResponse
import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "post")
class PostEntity(


	@Column(name = "contents")
	var contents: String,

	@Column(name = "title")
	var title: String,

	@CreatedDate
	var createdAt: LocalDateTime = LocalDateTime.now(),

	@Column
	val anonymousNickname : String,

	@ManyToOne(fetch = FetchType.LAZY)
	var member : MemberEntity,

	) {
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	var id: Long? = null

	fun toResponse(): PostResponse {
		return PostResponse(
			contents = contents,
			title =  title,
			created_at = createdAt,
			anonymous_nickname = anonymousNickname
			// postresponse에는 익명이 없다
		)
	}
}
