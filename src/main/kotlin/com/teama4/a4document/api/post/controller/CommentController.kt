package com.teama4.a4document.api.post.controller

import com.teama4.a4document.api.post.service.CommentApiService
import com.teama4.a4document.domain.post.comment.dto.CommentResponse
import com.teama4.a4document.domain.post.comment.dto.CreatCommentRequest
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentRequest
import com.teama4.a4document.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "comments", description = "댓글 API")
@RequestMapping("/post/{postId}/comments")
@RestController
class CommentController(
	val commentApiService: CommentApiService
) {
	@Operation(summary = "댓글 작성", description = "postId를 기준으로 댓글을 작성합니다.")
	@PostMapping
	fun createComment(
		@PathVariable postId: Long,
		@Valid @RequestBody creatCommentRequest: CreatCommentRequest,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<CommentResponse> {
		val result = commentApiService.createComment(creatCommentRequest, postId, userPrincipal)
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(result)
	}

	@Operation(summary = "댓글 목록 조회", description = "댓글 목록을 가져옵니다.")
	@GetMapping
	fun findAllByPostId(
		@PathVariable postId: Long
	): ResponseEntity<List<CommentResponse>> {
		val result = commentApiService.findAllByPostId(postId)
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(result)
	}

	@Operation(summary = "댓글 수정", description = "postId, commentId를 기준으로 댓글을 수정합니다.")
	@PutMapping("/{commentId}")
	fun updateComment(
		@PathVariable commentId: Long,
		@Valid @RequestBody updateCommentRequest: UpdateCommentRequest,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<CommentResponse> {
		val comment = commentApiService.updateComment(
			updateCommentRequest, commentId, userPrincipal
		)
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(comment)
	}

	@Operation(summary = "댓글 삭제", description = "postId, commentId를 기준으로 댓글을 삭제합니다.")
	@DeleteMapping("/{commentId}")
	fun deletePost(
		@PathVariable commentId: Long,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<Unit> {
		commentApiService.deleteComment(commentId, userPrincipal)
		return ResponseEntity
			.status(HttpStatus.OK)
			.build()
	}
}