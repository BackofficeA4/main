package com.teama4.a4document.domain.post.comment.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "Comment")
class CommentEntity(

	@Column
	var content: String,

	@Column
	val memberId: Long,

	@Column
	val postId: Long,

	) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val commentId: Long? = null

	@CreatedDate
	@Column(name = "create_at")
	var createAt: LocalDateTime? = null

	@CreatedBy
	@Column(name = "create_name")
	var createName: String? = null


	fun changeUpdateComment(updateCommentArguments: UpdateCommentArguments) {
		this.content = updateCommentArguments.content
	}

}