package com.teama4.a4document.api.member.controller

import com.teama4.a4document.api.member.service.MemberApiService
import com.teama4.a4document.common.member.auth.dto.UpdateMemberRequest
import com.teama4.a4document.common.member.auth.dto.UserInfoRequest
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
	private val memberApiService: MemberApiService
) {

	@GetMapping("/profile/check/auth")
	fun checkAuth(@AuthenticationPrincipal userPrincipal: UserPrincipal) =
		memberApiService.checkHasInfo(userPrincipal)

	@PostMapping("/profile/info")
	fun setInfo(
		@AuthenticationPrincipal userPrincipal: UserPrincipal,
		@RequestBody userInfoRequest: UserInfoRequest
	) =
		memberApiService.setInfo(userPrincipal, userInfoRequest)


	@PatchMapping("/profile/update")
	fun updateUserProfile(
		@AuthenticationPrincipal userPrincipal: UserPrincipal,
		@RequestBody updateMemberRequest: UpdateMemberRequest
	) =
		memberApiService.updateUserProfile(userPrincipal, updateMemberRequest)

}