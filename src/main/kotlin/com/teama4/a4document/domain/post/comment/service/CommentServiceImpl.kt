package com.teama4.a4document.domain.post.comment.service


import com.teama4.a4document.domain.post.comment.dto.CommentDto
import com.teama4.a4document.domain.post.comment.dto.CreatCommentArguments
import com.teama4.a4document.domain.post.comment.dto.UpdateCommentArguments
import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import com.teama4.a4document.domain.post.comment.repository.CommentRepository
import com.teama4.a4document.domain.post.repository.PostRepository
import com.teama4.a4document.common.member.entity.CustomMember
import com.teama4.a4document.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    val commentRepository: CommentRepository,
    val postRepository: PostRepository
) : CommentService {
    @Transactional
    override fun createComment(
        creatCommentArguments: CreatCommentArguments,
        postId: Long,
        user: CustomUser
    ): CommentDto {
        val targetPost = postRepository.findByIdOrNull(postId)
            ?: throw ModelNotFoundException("post", postId)
        val commentEntity = CommentEntity(
            content = creatCommentArguments.content,
            postId = targetPost.postId!!,
            userId = user.username.toLong()
        )
        val result = commentRepository.save(commentEntity)
        return CommentDto.from(result)
    }


    override fun findByCommentId(commentId: Long): CommentDto {
        val foundComment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("comment", commentId)
        return foundComment.let { CommentDto.from(it) }
    }

    override fun findAllCommentList(postId: Long): List<CommentDto> {
        val foundComments = commentRepository.findAllByPostId(postId)
        return foundComments.map { CommentDto.from(it) }
    }

    @Transactional
    override fun updateComment(
        updateCommentArguments: UpdateCommentArguments,
        postId: Long,
        commentId: Long
    ): CommentDto {
        val foundComment = commentRepository.findByPostIdAndCommentId(postId, commentId)
            ?: throw ModelNotFoundException("comment", commentId)

        foundComment.changeUpdateComment(updateCommentArguments)
        commentRepository.save(foundComment)
        return CommentDto.from(foundComment)
    }

    @Transactional
    override fun deleteComment(postId: Long, commentId: Long) {
        val foundComment = commentRepository.findByPostIdAndCommentId(postId, commentId)
            ?: throw ModelNotFoundException("comment", commentId)

        commentRepository.delete(foundComment)
    }

    override fun getCreatedId(postId: Long, commentId: Long): Long {
        return commentRepository.findByPostIdAndCommentId(postId, commentId)?.userId
            ?: throw ModelNotFoundException("post", postId)
    }
}