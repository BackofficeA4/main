package com.teama4.a4document.api.post.service

import com.teama4.a4document.domain.exception.ModelNotFoundException
import com.teama4.a4document.domain.post.comment.dto.CommentResponseDto
import com.teama4.a4document.domain.post.comment.dto.CreatCommentDto
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentDto
import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import com.teama4.a4document.domain.post.comment.service.CommentService
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CommentApiService(
	val commentService: CommentService
) {
	fun createComment(
		creatCommentDto: CreatCommentDto,
		postId: Long,
		userPrincipal: UserPrincipal
	): CommentResponseDto {
		TODO("Not yet implemented")
	}

	fun findByCommentId(commentId: Long): CommentResponseDto {
		TODO("Not yet implemented")
	}

	fun findAllCommentList(postId: Long): List<CommentResponseDto> {
		TODO("Not yet implemented")
	}

	fun updateComment(
		updateCommentDto: UpdateCommentDto,
		postId: Long,
		commentId: Long,
		userPrincipal: UserPrincipal
	): CommentResponseDto {
		TODO("Not yet implemented")
	}

	fun deleteComment(postId: Long, commentId: Long, userPrincipal: UserPrincipal) {
		TODO("Not yet implemented")
	}

}