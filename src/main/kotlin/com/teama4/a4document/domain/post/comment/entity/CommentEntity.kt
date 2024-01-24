package com.teama4.a4document.domain.post.comment.entity

import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentDto
import com.teama4.a4document.domain.post.entity.PostEntity
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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
	var content: String,


	@ManyToOne(fetch = FetchType.LAZY)
	val memberId: MemberEntity,

	@ManyToOne(fetch = FetchType.LAZY)
	val postEntity: PostEntity

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

	@LastModifiedDate
	@Column(name = "update_at")
	var updateAt: LocalDateTime? = null

	fun changeUpdateComment(updateCommentDto: UpdateCommentDto) {
		this.content = updateCommentDto.content
	}

}