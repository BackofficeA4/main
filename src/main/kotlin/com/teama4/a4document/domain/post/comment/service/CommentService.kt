package com.teama4.a4document.domain.post.comment.service



import com.teama4.a4document.domain.exception.ModelNotFoundException
import com.teama4.a4document.domain.post.repository.PostRepository
import com.teama4.a4document.domain.user.model.CustomUser
import com.teama4.a4document.domain.post.comment.dto.CommentResponseDto
import com.teama4.a4document.domain.post.comment.dto.CreatCommentDto
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentDto
import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import com.teama4.a4document.domain.post.comment.repository.CommentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    val commentRepository: CommentRepository,
    val postRepository: PostRepository
) {
    @Transactional
    fun createComment(
        creatCommentArguments: CreatCommentDto,
        postId: Long,
        user: CustomUser
    ): CommentResponseDto {
        val targetPost = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val commentEntity = CommentEntity(
            content = creatCommentArguments.content,
            postId = targetPost.postId!!,
            userId = user.username.toLong()
        )
        val result = commentRepository.save(commentEntity)
        return CommentResponseDto
    }

    fun findByCommentId(commentId: Long): CommentResponseDto {
        val foundComment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("comment", commentId)
        return foundComment
    }

    fun findAllCommentList(postId: Long): List<CommentResponseDto> {
        val foundComments = commentRepository.findAllByPostId(postId)
        return foundComments
    }

    @Transactional
    fun updateComment(
        updateCommentArguments: UpdateCommentDto,
        postId: Long,
        commentId: Long
    ): CommentResponseDto {
        val foundComment = commentRepository.findByPostIdAndCommentId(postId, commentId)
            ?: throw ModelNotFoundException("comment", commentId)

        foundComment.changeUpdateComment(updateCommentArguments)
        commentRepository.save(foundComment)
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