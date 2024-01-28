package com.teama4.a4document.common.member.auth.jwt

import com.teama4.a4document.common.member.auth.jwt.exception.JwtAuthenticationException
import com.teama4.a4document.common.member.auth.jwt.token.JwtAuthenticationToken
import com.teama4.a4document.common.member.auth.jwt.token.JwtPreAuthenticationToken
import com.teama4.a4document.common.member.repository.MemberRepository
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component


@Component
class JwtAuthenticationProvider(
	private val memberRepository: MemberRepository,
	private val jwtPlugin: JwtPlugin
) : AuthenticationProvider {

	private fun generateAuthenticationToken(principal: UserPrincipal) =
		JwtAuthenticationToken(principal = principal)

	private fun loadUser(userId: String, role: String, token: String) =
		memberRepository.findByEmail(userId)
			?.also { member ->
				member.refresh?.let { refresh ->
					jwtPlugin.validateToken(refresh).getOrElse { throw JwtAuthenticationException(it as Exception) }
				} ?: throw TODO("로그아웃 처리된 사용자 - 모든 기기 로그아웃 처리")
			}
			?.let { UserPrincipal(userId, setOf(role), token) }
			?: throw TODO("멤버 관련 정보 찾지 못함")

	private fun getAuthentication(userId: String, role: String, token: String) =
		generateAuthenticationToken(loadUser(userId, role, token))


	override fun authenticate(authentication: Authentication): Authentication {
		return (authentication.credentials as String)
			.let { jwt ->
				jwtPlugin.validateToken(jwt)
					.getOrElse { throw JwtAuthenticationException(it as Exception) }
					.let { claims ->
						val id = claims.payload.subject
						val role = claims.payload.get("role", String::class.java)
						getAuthentication(id, role, jwt)
					}
			}
	}

	override fun supports(authentication: Class<*>): Boolean =
		JwtPreAuthenticationToken::class.java.isAssignableFrom(authentication)
}