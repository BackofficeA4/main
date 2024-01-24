package com.teama4.a4document.domain.exception

data class ForbiddenException(
    override val message: String
) : RuntimeException(message)

