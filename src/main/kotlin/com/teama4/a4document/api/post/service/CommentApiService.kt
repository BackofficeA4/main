package com.teama4.a4document.api.post.service

import com.teama4.a4document.domain.post.comment.dto.CommentResponseDto
import com.teama4.a4document.domain.post.comment.dto.CreatCommentDto
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentDto
import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import com.teama4.a4document.domain.post.comment.service.CommentService
import org.springframework.stereotype.Service


@Service
class CommentController(

    private val commentService: CommentService

) {

    fun createComment(createCommentDto: CreatCommentDto, postId:Long, user:CustomUser):CommentResponseDto{
        return commentService.createComment(createCommentDto,postId,user)
    }

    fun findByCommentId(commentId: Long):CommentEntity{
        return commentService.findByCommentId(commentId)
    }

    fun findAllCommentList(postId: Long):List<CommentResponseDto>{
        return commentService.findAllCommentList(postId)
    }

    fun updateComment(updateCommentDto: UpdateCommentDto, postId: Long, commentId: Long):CommentResponseDto{
        return commentService.updateComment(updateCommentDto,postId,commentId)
    }

    fun deleteComment(postId: Long, commentId: Long){
        return commentService.deleteComment(postId,commentId)
    }

    fun getCreatedId(postId: Long, commentId: Long):Long{
        return commentService.getCreatedId(postId,commentId)
    }




}