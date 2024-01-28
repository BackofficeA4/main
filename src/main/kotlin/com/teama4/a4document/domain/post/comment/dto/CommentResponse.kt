package com.teama4.a4document.domain.post.comment.dto

import java.time.LocalDateTime


data class CommentResponse(
	val anonymousNickname: String,
	val text: String,
	val createAt: LocalDateTime,
)
