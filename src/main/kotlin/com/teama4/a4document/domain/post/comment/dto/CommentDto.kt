package com.teama4.a4document.domain.post.comment.dto

import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime


@Schema(description = "댓글을 수정할 때 입력한 정보를 전달하는 객체")
data class CommentDto(

    val id: Long?,
    val content: String,
    val createAt: LocalDateTime?,
    val createName: String?,
    val updateAt: LocalDateTime?,
    val postId: Long?

) {

    companion object {
        fun from(comment: CommentEntity): CommentDto {
            return CommentDto(
                id = comment.commentId,
                content = comment.content,
                createAt = comment.createAt,
                createName = comment.createName,
                updateAt = comment.updateAt,
                postId = comment.postId
            )
        }
    }

}
