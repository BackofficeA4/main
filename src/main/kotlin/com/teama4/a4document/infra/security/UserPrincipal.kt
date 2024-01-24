package com.teama4.a4document.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority


data class UserPrincipal(
	val memberEmail: String,
	val authorities: Collection<GrantedAuthority>
) {
	constructor(member: String, roles: Set<String>) : this(
		member,
		roles.map { SimpleGrantedAuthority("ROLE_$it") }
	)
}