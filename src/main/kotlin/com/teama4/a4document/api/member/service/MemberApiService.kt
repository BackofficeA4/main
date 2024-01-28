package com.teama4.a4document.api.member.service

import com.teama4.a4document.common.member.auth.dto.UpdateMemberRequest
import com.teama4.a4document.common.member.auth.dto.UserInfoRequest
import com.teama4.a4document.common.member.auth.util.AuthProfileManager
import com.teama4.a4document.common.member.service.MemberService
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.stereotype.Service

@Service
class MemberApiService(
	private val memberService: MemberService,
) {
	fun checkHasInfo(userPrincipal: UserPrincipal) =
		AuthProfileManager.checkHasInfo(userPrincipal.token)

	fun setInfo(userPrincipal: UserPrincipal, userInfoRequest: UserInfoRequest) {
		takeIf { memberService.checkPassword(userPrincipal, userInfoRequest.password) }
			?.let { AuthProfileManager.setInfo(userPrincipal.token) }
			?: throw TODO("비밀번호가 일치하지 않음")
	}

	fun updateUserProfile(userPrincipal: UserPrincipal, updateMemberRequest: UpdateMemberRequest) =
		takeIf { AuthProfileManager.checkHasInfo(userPrincipal.token) }
			?.let { memberService.changeProfile(userPrincipal, updateMemberRequest) }
			?: throw TODO("해당 정보가 등록되어 있지 않거나 만료됨 - 재인증")
}