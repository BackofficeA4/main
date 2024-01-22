package com.teama4.a4document.common.member.entity

import com.teama4.a4document.common.member.type.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberEntity(

	@Column(name = "email", nullable = false)
	var email: String?,


	@Column(name = "password", nullable = false)
	var password: String?,

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	val role: UserRole = UserRole.USER,


//	@Enumerated(EnumType.STRING)
//	@Column(name = "status", nullable = false)
//	val status: Status = Status.FALSE,

	@Column(name = "key")
	val key: String
){
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null
}