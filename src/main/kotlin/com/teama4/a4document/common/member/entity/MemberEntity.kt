package com.teama4.a4document.common.member.entity

import com.teama4.a4document.common.member.dto.SignupResponse
import com.teama4.a4document.common.member.type.UserRole
import io.hypersistence.utils.hibernate.type.array.StringArrayType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "member")
class MemberEntity(

	@Column(name = "email", unique = true)
	var email: String,

	@Column(name = "password")
	var password: String,

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	val role: UserRole = UserRole.ROLE_USER,

	@Column(name = "refresh")
	var refresh: String?,

	@Type(value = StringArrayType::class)
	@Column(name = "password_list", columnDefinition = "text[]")
	var passwordList: Array<String>
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null

	fun updatePasswordList(passwordList: Array<String>) {
		this.passwordList = passwordList
	}
}

fun MemberEntity.toSignupResponse(): SignupResponse {
	return SignupResponse(
		email = email
	)
}