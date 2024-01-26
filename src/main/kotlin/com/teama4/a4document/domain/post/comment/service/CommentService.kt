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
) {
	@Transactional
	fun createComment(
		creatCommentRequest: CreatCommentRequest,
		postEntity: PostEntity,
		memberEntity:MemberEntity
	): CommentResponse {
		    // 포스트 값을 찾을 필요가 없다. 포스트 엔티티를 찾아줬으니 레파지토리는 따로 필요없다.
		// 레파지토리는 저장소 ,  엔티티는 데이터 베이스 데이터를 바로 쏴주니 레파지토리는 따로 써줄 필요없다
		val commentEntity = CommentEntity(
			text = creatCommentRequest.text,
			post = postEntity,
			member = memberEntity
		)
	return commentRepository.save(commentEntity).toResponse()
		//   post 값과 member 값을 가져와야 하고 comment service니 comment entity 값도 받아야한다?
	}

	fun findAllByPostId(postId: Long): List<CommentResponse> {
	return commentRepository.findAllByPostId(postId).map {it.toResponse()}
//		다수건 조회를 해야하는데 단전 조회를 하고있었다.          it 이 comment entity
		//  레파지토리안에있는 findAllByPostId 통해서 comment entity들을 가져오고  response로 전부 변환 시켜서 return해주었다.

	}

	@Transactional
	fun updateComment(
		updateCommentRequest: UpdateCommentRequest,
		commentId: Long,
		userPrincipal:UserPrincipal,
	): CommentResponse {
		val foundComment = commentRepository.findByIdOrNull(commentId)
			?: throw ModelNotFoundException("comment", commentId)
//		val (text) = updateCommentRequest

		foundComment.text = updateCommentRequest.text

		return commentRepository.save(foundComment).toResponse()
	}

	@Transactional
	fun deleteComment(commentId: Long , userPrincipal:UserPrincipal) {
		val foundComment = commentRepository.findByIdOrNull(commentId)
			?: throw ModelNotFoundException("comment", commentId)
		commentRepository.delete(foundComment)
	}

}