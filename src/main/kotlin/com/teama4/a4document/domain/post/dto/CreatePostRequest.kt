package com.teama4.a4document.domain.post.dto

import java.time.LocalDateTime

data class CreatePostRequest(
    val contents : String,
    val title : String,
)
