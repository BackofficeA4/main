package com.teama4.a4document.api.auth.controller

import com.teama4.a4document.common.member.dto.SignInResponse
import com.teama4.a4document.common.member.dto.SignRequest
import com.teama4.a4document.common.member.dto.SignupResponse
import com.teama4.a4document.common.member.service.MemberService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "회원", description = "회원가입 및 로그인")
@RestController
@RequestMapping("/auth")
class MemberController(
	private val memberService: MemberService
) {
	@PostMapping("/signup")
	fun signup(@RequestBody signRequest: SignRequest): ResponseEntity<SignupResponse> {
		return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signUp(signRequest))
	}

	@PostMapping("/signin")
	fun signIn(@RequestBody signRequest: SignRequest): ResponseEntity<SignInResponse> {
		return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signIn(signRequest))
	}
}