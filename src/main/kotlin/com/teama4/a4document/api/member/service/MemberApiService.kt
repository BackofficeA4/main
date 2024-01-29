package com.teama4.a4document.api.member.service

import com.teama4.a4document.common.member.auth.dto.UpdateMemberRequest
import com.teama4.a4document.common.member.auth.dto.UserInfoRequest
import com.teama4.a4document.common.member.auth.util.AuthProfileManager
import com.teama4.a4document.common.member.exception.PasswordMismatchException
import com.teama4.a4document.common.member.exception.ProfileExpirationException
import com.teama4.a4document.common.member.service.MemberService
import com.teama4.a4document.infra.security.UserPrincipal
import com.teama4.a4document.system.errorobject.ErrorCode
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
			?: throw PasswordMismatchException(ErrorCode.MEMBER_PASSWORD_MISMATCH)
	}

	fun updateUserProfile(userPrincipal: UserPrincipal, updateMemberRequest: UpdateMemberRequest) =
		takeIf { AuthProfileManager.checkHasInfo(userPrincipal.token) }
			?.let { memberService.changeProfile(userPrincipal, updateMemberRequest) }
			?: throw ProfileExpirationException(ErrorCode.MEMBER_PROFILE_EXPIRATION)
}