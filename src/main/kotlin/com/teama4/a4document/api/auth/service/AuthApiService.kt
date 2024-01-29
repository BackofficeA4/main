package com.teama4.a4document.api.auth.service

import com.teama4.a4document.common.member.dto.SignRequest
import com.teama4.a4document.common.member.service.MemberService
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.stereotype.Service

@Service
class AuthApiService(
	private val memberService: MemberService
) {
	fun signUp(signRequest: SignRequest) =
		memberService.signUp(signRequest)

	fun signIn(signRequest: SignRequest) =
		memberService.signIn(signRequest)

	fun logout(userPrincipal: UserPrincipal) {
		memberService.logout(userPrincipal)
	}
}