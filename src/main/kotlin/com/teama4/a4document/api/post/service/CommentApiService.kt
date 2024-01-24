package com.teama4.a4document.api.post.service

import com.teama4.a4document.domain.exception.ModelNotFoundException
import com.teama4.a4document.domain.post.comment.dto.CommentResponseDto
import com.teama4.a4document.domain.post.comment.dto.CreatCommentDto
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentDto
import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import com.teama4.a4document.domain.post.comment.service.CommentService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CommentApiService(

    val commentService: CommentService

) {

    @Transactional
    fun createComment(
        creatCommentDto: CreatCommentDto,
        postId: Long,
        user: CustomUser
    ): CommentResponseDto {
        return CommentResponseDto
    }


    fun findByCommentId(commentId: Long): CommentEntity {
        val foundComment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("comment", commentId)
        return foundComment
    }

    fun findAllCommentList(postId: Long): List<CommentEntity> {
        val foundComments = commentRepository.findAllByPostId(postId)
        return foundComments
    }

    @Transactional
    fun updateComment(
        updateCommentDto: UpdateCommentDto,
        postId: Long,
        commentId: Long
    ): CommentResponseDto {
        return CommentResponseDto
    }

    @Transactional
    fun deleteComment(postId: Long, commentId: Long) {
        val foundComment = commentRepository.findByPostIdAndCommentId(postId, commentId)
            ?: throw ModelNotFoundException("comment", commentId)

        commentRepository.delete(foundComment)
    }

    fun getCreatedId(postId: Long, commentId: Long): Long {
        return commentRepository.findByPostIdAndCommentId(postId, commentId)?.userId
            ?: throw ModelNotFoundException("post", postId)
    }



}