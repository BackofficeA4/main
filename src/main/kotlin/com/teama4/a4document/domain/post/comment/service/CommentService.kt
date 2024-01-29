package com.teama4.a4document.domain.post.comment.service


import com.teama4.a4document.common.member.auth.util.checkAuthor
import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.system.exception.ModelNotFoundException
import com.teama4.a4document.domain.post.comment.dto.CommentResponse
import com.teama4.a4document.domain.post.comment.dto.CreatCommentRequest
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentRequest
import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import com.teama4.a4document.domain.post.comment.repository.CommentRepository
import com.teama4.a4document.domain.post.entity.PostEntity
import com.teama4.a4document.infra.security.UserPrincipal
import com.teama4.a4document.system.errorobject.ErrorCode
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
	val commentRepository: CommentRepository,
) {

	@Transactional
	fun createComment(
		creatCommentRequest: CreatCommentRequest,
		postEntity: PostEntity,
		memberEntity: MemberEntity
	): CommentResponse {
		val commentEntity = CommentEntity(
			text = creatCommentRequest.text,
			post = postEntity,
			member = memberEntity,
			anonymousNickname = RandomStringUtils.random(10, true, true)
		)
		return commentRepository.save(commentEntity).toResponse()
	}

	fun findAllByPostId(postId: Long): List<CommentResponse> {
		return commentRepository.findAllByPostId(postId).map { it.toResponse() }
	}

	@Transactional
	fun updateComment(
		updateCommentRequest: UpdateCommentRequest,
		commentId: Long,
		userPrincipal: UserPrincipal,
	): CommentResponse {
		val foundComment = commentRepository.findByIdOrNull(commentId)
			?: throw throw ModelNotFoundException(ErrorCode.MODEL_NOT_FOUND)

		checkAuthor(userPrincipal, foundComment.member)

		foundComment.text = updateCommentRequest.text

		return commentRepository.save(foundComment).toResponse()
	}

	@Transactional
	fun deleteComment(commentId: Long, userPrincipal: UserPrincipal) {
		val foundComment = commentRepository.findByIdOrNull(commentId)
			?: throw throw ModelNotFoundException(ErrorCode.MODEL_NOT_FOUND)

		checkAuthor(userPrincipal, foundComment.member)

		commentRepository.delete(foundComment)
	}
}