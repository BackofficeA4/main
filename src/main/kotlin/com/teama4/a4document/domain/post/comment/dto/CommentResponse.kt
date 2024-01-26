package com.teama4.a4document.domain.post.comment.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime



data class CommentResponse(

    val text : String,
    val create_at: LocalDateTime?

)
