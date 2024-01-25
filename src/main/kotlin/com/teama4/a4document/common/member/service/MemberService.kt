package com.teama4.a4document.common.member.service

import com.teama4.a4document.common.member.auth.jwt.JwtPlugin
import com.teama4.a4document.common.member.dto.SignRequest
import com.teama4.a4document.common.member.dto.SignInResponse
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
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) {

    @Transactional
    fun signUp(signRequest: SignRequest): SignupResponse {
        if (memberRepository.existsByEmail(signRequest.email)) throw DuplicateAccess()
        val member = MemberEntity(
            email = signRequest.email,
            password = passwordEncoder.encode(signRequest.password),
            role = UserRole.USER,
            refresh = null
        )
        memberRepository.save(member)
        return member.toSignupResponse()
    }

    @Transactional
    fun signIn(signRequest: SignRequest): SignInResponse {
        val member = memberRepository.findByEmail(signRequest.email) ?: TODO("가입된 이메일 없을 때 예외처리")
        if(!passwordEncoder.matches(signRequest.password, member.password)) throw PasswordMismatchException()
        val accessToken = jwtPlugin.generateAccessToken(signRequest.email, role = member.role.name)
        val refreshToken = jwtPlugin.generateRefreshToken(signRequest.email, role = member.role.name)

        return SignInResponse(
            email = signRequest.email,
            accessToken = accessToken,
            refreshToken = refreshToken
        )

    }

}