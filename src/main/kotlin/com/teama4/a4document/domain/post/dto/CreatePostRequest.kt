package com.teama4.a4document.domain.post.dto

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class CreatePostRequest(
    @field:NotBlank(message = "contents는 필수입니다.")
    val contents : String,

    @field:NotBlank(message = "title은 필수입니다.")
    val title : String,
)
