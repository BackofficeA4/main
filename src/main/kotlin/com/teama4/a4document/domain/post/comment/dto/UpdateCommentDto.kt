package com.teama4.a4document.domain.post.comment.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "댓글을 수정할 때 입력한 정보를 전달하는 객체")
data class UpdateCommentDto(
    @Schema(description = "수정한 댓글 내용", example = "수정한 댓글 내용")
    val content: String
)