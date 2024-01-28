package com.teama4.a4document.api.post.service

import com.teama4.a4document.common.member.repository.MemberRepository
import com.teama4.a4document.domain.post.comment.dto.CommentResponse
import com.teama4.a4document.domain.post.comment.dto.CreatCommentRequest
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentRequest
import com.teama4.a4document.domain.post.comment.service.CommentService
import com.teama4.a4document.domain.post.repository.PostRepository
import com.teama4.a4document.infra.security.UserPrincipal
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class CommentApiService(
	private val commentService: CommentService,
	private val postRepository: PostRepository,
	private val memberRepository: MemberRepository
) {
	@Transactional
	fun createComment(
		creatCommentRequest: CreatCommentRequest,
		postId: Long,
		userPrincipal: UserPrincipal
	): CommentResponse {
		val post = postRepository.findByIdOrNull(postId) ?: throw TODO("post못찾음")
		val member = memberRepository.findByEmail(userPrincipal.memberEmail) ?: throw TODO()
		return commentService.createComment(creatCommentRequest, post, member)
	}

	fun findAllByPostId(postId: Long): List<CommentResponse> {
		return commentService.findAllByPostId(postId)
	}

	@Transactional
	fun updateComment(
		updateCommentRequest: UpdateCommentRequest,
		commentId: Long,
		userPrincipal: UserPrincipal,

		): CommentResponse {
		return commentService.updateComment(
			updateCommentRequest, commentId, userPrincipal
		)
	}

	@Transactional
	fun deleteComment(commentId: Long, userPrincipal: UserPrincipal) {
		return commentService.deleteComment(commentId, userPrincipal)
	}
}