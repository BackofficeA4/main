package com.teama4.a4document.common.member.auth.jwt


import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken
import java.io.Serializable

class JwtAuthenticationToken(
	private val principal: UserPrincipal,
) : AbstractAuthenticationToken(principal.authorities), Serializable {

	init {
		super.setAuthenticated(true)
	}

	override fun setAuthenticated(authenticated: Boolean) {
		super.setAuthenticated(authenticated)
	}
	override fun getPrincipal() = principal
	override fun getCredentials() = null
}