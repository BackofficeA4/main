package com.teama4.a4document.common.member.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class SignRequest(
    @field:NotBlank(message = "")
    @field:Pattern(regexp = "", message = "")
    val email: String,

    @field:NotBlank(message = "")
    @field:Pattern(regexp = "", message = "")
    val password: String
)