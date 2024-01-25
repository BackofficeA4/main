package com.teama4.a4document.common.member.auth.jwt.token

import org.springframework.security.authentication.AbstractAuthenticationToken

class JwtPreAuthenticationToken(
	private val token: String
): AbstractAuthenticationToken(null) {
	override fun getPrincipal() = null
	override fun getCredentials() = token
	override fun isAuthenticated(): Boolean = false
}