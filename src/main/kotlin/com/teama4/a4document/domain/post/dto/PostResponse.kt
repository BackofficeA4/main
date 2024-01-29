package com.teama4.a4document.domain.post.dto

import java.time.LocalDateTime

data class PostResponse(
	val contents: String,
	val title: String,
	val createdAt: LocalDateTime,
	val anonymousNickname: String
)
