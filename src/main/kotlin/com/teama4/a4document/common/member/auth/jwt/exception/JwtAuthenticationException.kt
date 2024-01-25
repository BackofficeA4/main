package com.teama4.a4document.common.member.auth.jwt.exception

import com.teama4.a4document.system.errorobject.ErrorCode
import org.springframework.security.core.AuthenticationException


class JwtAuthenticationException(val exception: Exception) : AuthenticationException("JwtException", exception) {
}