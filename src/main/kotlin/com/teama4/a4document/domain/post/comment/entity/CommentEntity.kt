package com.teama4.a4document.domain.post.comment.entity

import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.domain.dto.PostResponse
import com.teama4.a4document.domain.post.comment.dto.CommentResponse
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentRequest
import com.teama4.a4document.domain.post.entity.PostEntity
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
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
	var createAt: LocalDateTime? = null,

	@ManyToOne(fetch = FetchType.LAZY)
	val member: MemberEntity,

	@ManyToOne(fetch = FetchType.LAZY)
	val post: PostEntity

	) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val comment: Long? = null

	fun changeUpdateComment(updateCommentRequest: UpdateCommentRequest) {
		this.text = updateCommentRequest.text
	}

	fun toResponse(): CommentResponse {
		return CommentResponse(
			text = text,
			create_at = createAt
		)
	}


}