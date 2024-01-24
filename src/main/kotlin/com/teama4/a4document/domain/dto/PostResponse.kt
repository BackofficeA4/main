package com.teama4.a4document.domain.dto

import java.time.LocalDateTime

data class PostResponse(
    val contents : String,
    val title : String,
    val created_at : LocalDateTime,
    val anonymous_nickname: String
    // postresponse에는 익명이 없다.
)
