package com.teama4.a4document.common.member.entity

import com.teama4.a4document.common.member.dto.SignupResponse
import com.teama4.a4document.common.member.type.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(

	@Column(name = "email", unique = true)
	var email: String,

	@Column(name = "password")
	var password: String,

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	val role: UserRole = UserRole.USER,


//	@Enumerated(EnumType.STRING)
//	@Column(name = "status", nullable = false)
//	val status: Status = Status.FALSE,

	@Column(name = "refresh")
	val refresh: String?
){
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null
}

fun MemberEntity.toSignupResponse(): SignupResponse {
	return SignupResponse(
		email = email
	)
}