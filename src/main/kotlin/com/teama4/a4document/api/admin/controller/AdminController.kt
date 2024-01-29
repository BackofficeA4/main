package com.teama4.a4document.api.admin.controller

import com.teama4.a4document.api.admin.service.AdminApiService
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/admin")
class AdminController(
	private val adminApiService: AdminApiService
) {

	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@GetMapping("/delete/member/{memberEmail}")
	fun deleteMember(@AuthenticationPrincipal userPrincipal: UserPrincipal, @PathVariable memberEmail: String) {
		adminApiService.deleteMember(userPrincipal, memberEmail)
	}

	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@GetMapping("/delete/post/{postId}")
	fun deletePost(@AuthenticationPrincipal userPrincipal: UserPrincipal, @PathVariable postId: Long) {
		adminApiService.deletePost(postId, userPrincipal)
	}

	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@GetMapping("/delete/comment/{commentId}")
	fun deleteComment(@AuthenticationPrincipal userPrincipal: UserPrincipal, @PathVariable commentId:Long) {
		adminApiService.deleteComment(commentId, userPrincipal)
	}
}