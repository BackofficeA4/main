package com.teama4.a4document.common.member.dto

data class SignInResponse(
    val email: String,
    val accessToken: String,
    val refreshToken: String
)