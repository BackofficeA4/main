package com.teama4.a4document.common.member.auth.jwt

import com.teama4.a4document.common.member.auth.jwt.exception.JwtAuthenticationException
import com.teama4.a4document.common.member.auth.jwt.token.JwtAuthenticationToken
import com.teama4.a4document.common.member.auth.jwt.token.JwtPreAuthenticationToken
import com.teama4.a4document.common.member.repository.MemberRepository
import com.teama4.a4document.domain.exception.ForbiddenException
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationProvider(
	private val memberRepository: MemberRepository,
	private val jwtPlugin: JwtPlugin
) : AuthenticationProvider {

	fun generateAuthenticationToken(principal: UserPrincipal) =
		JwtAuthenticationToken(principal = principal)

	/**
	 * TODO: Member 인증 과정
	 *  1. Token에서 가져온 Member가 있는지 확인 (Status를 사용하게 된다면 Status를 확인하여 탈퇴, 정지 여부 확인)
	 *  2. member에서 Refresh Token을 가져와 시간이 만료되었는지 확인
	 *  3. Refresh Token이 만료 되었을 경우 재로그인 필요 Exception
	 */
	fun loadUser(userId: String, role: String) =
		memberRepository.findByEmail(userId)
			?.let { UserPrincipal(userId, setOf(role)) }
			?: throw JwtAuthenticationException(ForbiddenException("test"))

	fun getAuthentication(userId: String, role: String) =
		generateAuthenticationToken(loadUser(userId, role))


	override fun authenticate(authentication: Authentication): Authentication {
		return (authentication.credentials as String)
			.let { jwt ->
				jwtPlugin.validateToken(jwt).getOrElse { throw JwtAuthenticationException(it as Exception) }
					.let { claims ->
						val id = claims.payload.subject
						val role = claims.payload.get("role", String::class.java)
						getAuthentication(id, role)
					}
			}
	}

	override fun supports(authentication: Class<*>): Boolean =
		JwtPreAuthenticationToken::class.java.isAssignableFrom(authentication)
}