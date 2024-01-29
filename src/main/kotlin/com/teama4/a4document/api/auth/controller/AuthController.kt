package com.teama4.a4document.api.auth.controller

import com.teama4.a4document.api.auth.service.AuthApiService
import com.teama4.a4document.common.member.dto.SignInResponse
import com.teama4.a4document.common.member.dto.SignRequest
import com.teama4.a4document.common.member.dto.SignupResponse
import com.teama4.a4document.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "회원", description = "회원가입 및 로그인")
@RestController
@RequestMapping("/auth")
class AuthController(
	private val authApiService: AuthApiService
) {
	@PostMapping("/signup")
	fun signup(@RequestBody signRequest: SignRequest): ResponseEntity<SignupResponse> {
		return ResponseEntity.status(HttpStatus.CREATED).body(authApiService.signUp(signRequest))
	}

	@PostMapping("/signin")
	fun signIn(@RequestBody signRequest: SignRequest): ResponseEntity<SignInResponse> {
		return ResponseEntity.status(HttpStatus.CREATED).body(authApiService.signIn(signRequest))
	}

	@GetMapping("/logout")
	fun logout(@AuthenticationPrincipal userPrincipal: UserPrincipal){
		authApiService.logout(userPrincipal)
	}
}