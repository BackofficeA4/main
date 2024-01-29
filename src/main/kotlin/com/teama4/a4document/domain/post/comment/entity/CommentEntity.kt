package com.teama4.a4document.domain.post.comment.entity

import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.domain.post.comment.dto.CommentResponse
import com.teama4.a4document.domain.post.entity.PostEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "Comment")
class CommentEntity(

	@Column
	var text: String,

	@CreatedDate
	@Column(name = "create_at")
	var createAt: LocalDateTime = LocalDateTime.now(),

	@ManyToOne(fetch = FetchType.LAZY)
	val member: MemberEntity,

	@ManyToOne(fetch = FetchType.LAZY)
	val post: PostEntity,

	val anonymousNickname: String
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val comment: Long? = null

	fun toResponse(): CommentResponse {
		return CommentResponse(
			text = text,
			createAt = createAt,
			anonymousNickname = anonymousNickname
		)
	}
}