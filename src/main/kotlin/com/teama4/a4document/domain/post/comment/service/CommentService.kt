package com.teama4.a4document.domain.post.comment.service


import com.teama4.a4document.domain.post.comment.dto.CommentResponseDto
import com.teama4.a4document.domain.post.comment.dto.CreatCommentDto
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentDto

interface CommentService {
    fun createComment(creatCommentArguments: CreatCommentDto, postId: Long, user: CustomUser): CommentResponseDto
    fun findByCommentId(commentId: Long): CommentResponseDto
    fun findAllCommentList(postId: Long): List<CommentResponseDto>
    fun updateComment(updateCommentArguments: UpdateCommentDto, postId: Long, commentId: Long): CommentResponseDto
    fun deleteComment(postId: Long, commentId: Long)

    fun getCreatedId(postId: Long, commentId: Long): Long
}