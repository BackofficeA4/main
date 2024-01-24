package com.teama4.a4document.domain.post.repository

import com.teama4.a4document.domain.post.entity.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long>