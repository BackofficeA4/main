package com.teama4.a4document.api.post.controller

import com.teama4.a4document.api.post.service.CommentApiService
import com.teama4.a4document.domain.exception.ForbiddenException
import com.teama4.a4document.domain.post.comment.dto.CommentResponseDto
import com.teama4.a4document.domain.post.comment.dto.CreatCommentDto
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "comments", description = "댓글 API")
@RequestMapping("/posts/{postId}/comments")
@RestController
class CommentController(

    val commentApiService: CommentApiService

) {

    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "댓글 작성", description = "postId를 기준으로 댓글을 작성합니다.")
    @PostMapping
    fun createComment(
        @PathVariable postId: Long,
        @Valid @RequestBody creatCommentDto: CreatCommentDto,
        @AuthenticationPrincipal user: CustomMember
    ): ResponseEntity<CommentResponseDto> {
        val result = commentApiService.createComment(creatCommentDto, postId, user)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }

    @Operation(summary = "댓글 단건 조회", description = "댓글을 조회합니다.")
    @GetMapping("/{commentId}")
    fun findComment(
        @PathVariable postId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<CommentResponseDto> {
        val result = commentApiService.findByCommentId(commentId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(result)
    }

    @Operation(summary = "댓글 목록 조회", description = "댓글 목록을 가져옵니다.")
    @GetMapping
    fun getCommentList(
        @PathVariable postId: Long
    ): ResponseEntity<List<CommentResponseDto>> {
        val result = commentApiService.findAllCommentList(postId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(result)
    }

    @Operation(summary = "댓글 수정", description = "postId, commentId를 기준으로 댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
        @Valid @RequestBody updateCommentDto: UpdateCommentDto,
        @AuthenticationPrincipal user: CustomMember
    ): ResponseEntity<CommentResponseDto> {
        if (commentApiService.getCreatedId(
                postId,
                commentId
            ) != user.username.toLong()
        ) throw ForbiddenException("수정 권한이 없습니다.")
        val arguments = UpdateCommentDto(
            content = updateCommentDto.content
        )
        val comment = commentApiService.updateComment(arguments, postId, commentId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(comment)
    }

    @Operation(summary = "댓글 삭제", description = "postId, commentId를 기준으로 댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    fun deletePost(
        @PathVariable postId: Long,
        @PathVariable commentId: Long,
        @AuthenticationPrincipal user: CustomMember
    ): ResponseEntity<Unit> {
        if (commentApiService.getCreatedId(
                postId,
                commentId
            ) != user.username.toLong()
        ) throw ForbiddenException("삭제 권한이 없습니다.")
        commentApiService.deleteComment(postId, commentId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }
}