package com.teama4.a4document.common.member.service

import com.teama4.a4document.common.member.dto.SignInRequest
import com.teama4.a4document.common.member.dto.SignInResponse
import com.teama4.a4document.common.member.dto.SignupRequest
import com.teama4.a4document.common.member.dto.SignupResponse
import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.common.member.entity.toSignupResponse
import com.teama4.a4document.common.member.exception.DuplicateAccess
import com.teama4.a4document.common.member.exception.PasswordMismatchException
import com.teama4.a4document.common.member.repository.MemberRepository
import com.teama4.a4document.common.member.type.UserRole
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder, // 암호화 구현체
    private val jwtPlugin: JwtPlugin // jwt 발급
) {

    // 회원가입
    @Transactional
    fun signUp(signUpRequest: SignupRequest): SignupResponse {
        if (memberRepository.existsByEmail(signUpRequest.email)) throw DuplicateAccess()
        val member = MemberEntity(
            email = signUpRequest.email,
            password = passwordEncoder.encode(signUpRequest.password),
            role = UserRole.USER,
            refresh = null
        )
        memberRepository.save(member)
        return member.toSignupResponse()
    }

    // 로그인
    @Transactional
    fun signIn(signInRequest: SignInRequest): SignInResponse {
        val member = memberRepository.findByEmail(signInRequest.email) ?: TODO("가입된 이메일 없을 때 예외처리")
        if(!passwordEncoder.matches(signInRequest.password, member.password)) throw PasswordMismatchException()
        val accessToken = jwtPlugin.makeAccessToken(signInRequest.email)
        val refreshToken = jwtPlugin.makeRefeshTokn(signInRequest.email)

        return SignInResponse(
            email = signInRequest.email,
            accessToken = accessToken,
            refreshToken = refreshToken
        )

    }

}