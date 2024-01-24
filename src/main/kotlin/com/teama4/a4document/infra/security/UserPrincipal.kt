package com.teama4.a4document.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority


data class UserPrincipal(
	val member: Long,
	val authorities: Collection<GrantedAuthority>
) {
	constructor(member: Long, roles: Set<String>) : this(
		member,
		roles.map { SimpleGrantedAuthority("ROLE_$it") }
	)
}