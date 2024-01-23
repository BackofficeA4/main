package com.teama4.a4document.common.member.auth.jwt

import com.sparta.todoapp.common.member.auth.jwt.JwtPlugin
import com.sparta.todoapp.common.member.auth.jwt.JwtPreAuthenticationToken
import com.teama4.a4document.infra.security.UserPrincipal

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
	import org.springframework.stereotype.Component

@Component
class JwtAuthenticationProvider(
	private val memberEntityRepository: MemberEntityRepository,
	private val jwtPlugin: JwtPlugin
) : AuthenticationProvider {

	fun generateAuthenticationToken(principal: UserPrincipal) =
		JwtAuthenticationToken(principal = principal)

	fun loadUser(userId: String, role: String) =
		memberEntityRepository.findByIdOrNull(userId)
			?.let { UserPrincipal(it, setOf(role)) }
			?: throw TODO("멤버 찾지 못함")

	fun getAuthentication(userId: String, role: String) =
		generateAuthenticationToken(loadUser(userId, role))


	override fun authenticate(authentication: Authentication?): Authentication {
		return (authentication?.credentials as String)
			.let { jwt ->
				jwtPlugin.validateToken(jwt)
					.onFailure {
						when(it){

							else -> {}
						}
					}.getOrThrow()
			}
			.let {
				val id = it.payload.subject
				val role = it.payload.get("role", String::class.java)
				getAuthentication(id, role)
			}
	}

	override fun supports(authentication: Class<*>?): Boolean =
		JwtPreAuthenticationToken::class.java.isAssignableFrom(authentication!!)
}