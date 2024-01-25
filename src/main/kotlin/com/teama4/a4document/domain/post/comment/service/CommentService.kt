package com.teama4.a4document.domain.post.comment.service


import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.domain.exception.ModelNotFoundException
import com.teama4.a4document.domain.post.comment.dto.CommentResponse
import com.teama4.a4document.domain.post.comment.dto.CreatCommentRequest
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentRequest
import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import com.teama4.a4document.domain.post.comment.repository.CommentRepository
import com.teama4.a4document.domain.post.entity.PostEntity
import com.teama4.a4document.domain.post.repository.PostRepository
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
	val commentRepository: CommentRepository,
	val postRepository: PostRepository
) {
	@Transactional
	fun createComment(
		creatCommentRequest: CreatCommentRequest,
		postEntity: PostEntity,
		memberEntity:MemberEntity
	): CommentResponse {
		val targetPost = postRepository.findByIdOrNull(postEntity)
			?: throw ModelNotFoundException("post", postEntity)
		val commentEntity = CommentEntity(
			text = creatCommentRequest.text,
			post = targetPost.postEntity!!,
			member = memberEntity
		)
		val result = commentRepository.save(commentEntity)
		return CommentResponse
	}

	fun findByPostIdAndCommentId(postId: Long,commentId: Long): CommentEntity {
		val foundComment = commentRepository.findByIdOrNull(commentId)
			?: throw ModelNotFoundException("comment", commentId)
		return foundComment
	}

	fun findAllByPostId(postId: Long): List<CommentEntity> {
		val foundComments = commentRepository.findAllByPostId(postId)
		return foundComments
	}

	@Transactional
	fun updateComment(
		updateCommentRequest: UpdateCommentRequest,
		postId: Long,
		commentId: Long
	): CommentResponse {
		val foundComment = commentRepository.findByPostIdAndCommentId(postId, commentId)
			?: throw ModelNotFoundException("comment", commentId)
		val (text) = updateCommentRequest

		foundComment.text = text

		return commentRepository.save(foundComment).toResponse()
	}

	@Transactional
	fun deleteComment(postId: Long, commentId: Long) {
		val foundComment = commentRepository.findByPostIdAndCommentId(postId, commentId)
			?: throw ModelNotFoundException("comment", commentId)
		commentRepository.delete(foundComment)
	}

}