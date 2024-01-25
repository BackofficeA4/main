package com.teama4.a4document.api.post.service

import com.teama4.a4document.domain.post.comment.dto.CommentResponse
import com.teama4.a4document.domain.post.comment.dto.CreatCommentRequest
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentRequest
import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import com.teama4.a4document.domain.post.comment.repository.CommentRepository
import com.teama4.a4document.domain.post.comment.service.CommentService
import com.teama4.a4document.domain.post.service.PostService
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.stereotype.Service


@Service
class CommentApiService(
	private val commentService: CommentService,
	private val commentRepository: CommentRepository
) {
	fun createComment(
		creatCommentRequest: CreatCommentRequest,
		postId: Long,
		userPrincipal: UserPrincipal
	): CommentResponse {
		return commentRepository.findAllByPostId(postId)
			?.let { commentService.createComment(creatCommentRequest, it) }
			?: TODO("멤버 못찾음")
	}

	fun findAllByPostId(commentId: Long): List<CommentEntity> {
		return commentRepository.findAllByPostId(postId)
	}

	fun findByPostIdAndCommentId(postId: Long): CommentEntity? {
		return commentRepository.findByPostIdAndCommentId(postId, commentId)
	}

	fun updateComment(
		updateCommentRequest: UpdateCommentRequest,
		postId: Long,
		commentId: Long,
		userPrincipal: UserPrincipal
	): CommentResponse {
		return commentService.updateComment(updateCommentRequest,commentId,postId)
	}

	fun deleteComment(postId: Long, commentId: Long, userPrincipal: UserPrincipal) {
		return commentService.deleteComment(postId,commentId)
	}

}