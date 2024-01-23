package com.teama4.a4document.domain.post.comment.service


import com.teama4.a4document.common.member.entity.CustomMember
import com.teama4.a4document.domain.post.comment.dto.CommentDto
import com.teama4.a4document.domain.post.comment.dto.CreatCommentArguments
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentArguments

interface CommentService {
    fun createComment(creatCommentArguments: CreatCommentArguments, postId: Long, user: CustomUser): CommentDto
    fun findByCommentId(commentId: Long): CommentDto
    fun findAllCommentList(postId: Long): List<CommentDto>
    fun updateComment(updateCommentArguments: UpdateCommentArguments, postId: Long, commentId: Long): CommentDto
    fun deleteComment(postId: Long, commentId: Long)

    fun getCreatedId(postId: Long, commentId: Long): Long
}