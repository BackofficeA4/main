package com.teama4.a4document.common.member.service

import com.teama4.a4document.common.member.auth.dto.UpdateMemberRequest
import com.teama4.a4document.common.member.auth.jwt.JwtPlugin
import com.teama4.a4document.common.member.dto.SignInResponse
import com.teama4.a4document.common.member.dto.SignRequest
import com.teama4.a4document.common.member.dto.SignupResponse
import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.common.member.entity.toSignupResponse
import com.teama4.a4document.common.member.exception.DuplicateAccess
import com.teama4.a4document.system.exception.ModelNotFoundException
import com.teama4.a4document.common.member.exception.PasswordMismatchException
import com.teama4.a4document.common.member.exception.RecentPasswordException
import com.teama4.a4document.common.member.repository.MemberRepository
import com.teama4.a4document.common.member.type.UserRole
import com.teama4.a4document.infra.security.UserPrincipal
import com.teama4.a4document.system.errorobject.ErrorCode
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
		if (memberRepository.existsByEmail(signRequest.email)) throw DuplicateAccess(ErrorCode.MEMBER_EMAIL_DUPLICATE)

		val member = MemberEntity(
			email = signRequest.email,
			password = passwordEncoder.encode(signRequest.password),
			role = UserRole.ROLE_USER,
			refresh = null,
			arrayOf(passwordEncoder.encode(signRequest.password))
		)
		memberRepository.save(member)
		return member.toSignupResponse()
	}

	@Transactional
	fun signIn(signRequest: SignRequest): SignInResponse {
		val member = memberRepository.findByEmail(signRequest.email)
			?: throw ModelNotFoundException(ErrorCode.MODEL_NOT_FOUND)
		if (!passwordEncoder.matches(
				signRequest.password,
				member.password
			)
		) throw PasswordMismatchException(ErrorCode.MEMBER_PASSWORD_MISMATCH)
		val accessToken = jwtPlugin.generateAccessToken(signRequest.email, role = member.role.name)
		val refreshToken = jwtPlugin.generateRefreshToken(signRequest.email, role = member.role.name)

		member.refresh = refreshToken

		return SignInResponse(
			email = signRequest.email,
			accessToken = accessToken,
			refreshToken = refreshToken
		)
	}

	fun checkPassword(userPrincipal: UserPrincipal, password: String) =
		memberRepository.findByEmail(userPrincipal.memberEmail)!!
			.let { passwordEncoder.matches(password, it.password) }


	fun changeProfile(userPrincipal: UserPrincipal, updateDto: UpdateMemberRequest) {
		memberRepository.findByEmail(userPrincipal.memberEmail)!!
			.run { updatePasswordList(passwordListUpdate(this.passwordList, updateDto.password)) }
	}

	private fun passwordListUpdate(passwordList: Array<String>, password: String) =
		passwordList.find { passwordEncoder.matches(password, it) }
			?.let { throw RecentPasswordException(ErrorCode.MEMBER_PASSWORD_RECENT) }
			?: let {
				val newPasswordList: MutableList<String> = passwordList.toMutableList()
				newPasswordList.add(passwordEncoder.encode(password))
				if (newPasswordList.size < 3) newPasswordList.removeAt(0)
				newPasswordList.toTypedArray()
			}

	fun deleteMember(userPrincipal: UserPrincipal, memberEmail: String) {
		memberRepository.findByEmail(memberEmail)
			?.let { memberRepository.delete(it) }
			?: ModelNotFoundException(ErrorCode.MODEL_NOT_FOUND)
	}

	@Transactional
	fun logout(userPrincipal: UserPrincipal) {
		memberRepository.findByEmail(userPrincipal.memberEmail)!!.refresh = null
	}
}