package com.teama4.a4document.api.post.service

import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.common.member.repository.MemberRepository
import com.teama4.a4document.domain.post.comment.dto.CommentResponse
import com.teama4.a4document.domain.post.comment.dto.CreatCommentRequest
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentRequest
import com.teama4.a4document.domain.post.comment.service.CommentService
import com.teama4.a4document.domain.post.entity.PostEntity
import com.teama4.a4document.domain.post.repository.PostRepository
import com.teama4.a4document.domain.post.service.PostService
import com.teama4.a4document.infra.security.UserPrincipal
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class CommentApiService(
	private val commentService: CommentService,
	private val postRepository: PostRepository,
	private val memberRepository:MemberRepository
) {
	@Transactional
	fun createComment(
		creatCommentRequest: CreatCommentRequest,
		postId: Long,
		userPrincipal: UserPrincipal
	): CommentResponse {
		val post = postRepository.findByIdOrNull(postId) ?: throw TODO("post못찾음")
		val member = memberRepository.findByEmail(userPrincipal.memberEmail) ?: throw TODO()
		//멤버 값을 가져오려면 맴버 레파지토리에서 유저 프린시플 값을 찾아야한다.
		//유저 프린시플에있는 멤버 이메일 값을 가져와야한다?
		// 유저 프린시플은 인증된 유저의 값
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
			updateCommentRequest,commentId,userPrincipal)
	}

	@Transactional
	fun deleteComment(commentId: Long ,userPrincipal:UserPrincipal ) {
		return commentService.deleteComment(commentId,userPrincipal)
	}
// 삭제에 포스트가 필요한가???
}