package com.teama4.a4document.common.member.auth.jwt

import com.teama4.a4document.common.member.repository.MemberRepository
import com.teama4.a4document.infra.security.UserPrincipal
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.IncorrectClaimException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.MissingClaimException
import io.jsonwebtoken.security.SignatureException

import org.springframework.data.repository.findByIdOrNull
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
			?: throw TODO("멤버 찾지 못함")

	fun getAuthentication(userId: String, role: String) =
		generateAuthenticationToken(loadUser(userId, role))


	override fun authenticate(authentication: Authentication?): Authentication {
		return (authentication?.credentials as String)
			.let { jwt ->
				jwtPlugin.validateToken(jwt)
					.onFailure {
						when(it){
							// CustomAuthenticationException 으로 감싸서 던져주기
							is SignatureException -> TODO("서명이 일치하지 않음")
							is MalformedJwtException -> TODO("제대로 된 JWT Token 아님")
							is IncorrectClaimException -> TODO("Claim 이 예상하던 값과 다름")
							is MissingClaimException -> TODO("Claim Token 누락")
							is ExpiredJwtException -> TODO("JWT Token 만료")
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