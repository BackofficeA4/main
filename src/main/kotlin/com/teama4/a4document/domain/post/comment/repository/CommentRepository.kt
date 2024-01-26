package com.teama4.a4document.domain.post.comment.repository


import com.teama4.a4document.domain.post.comment.entity.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long> {
    fun findAllByPostId(postId: Long): List<CommentEntity>

    fun findByPostIdAndCommentId(postId: Long, commentId: Long): CommentEntity?

}