package com.teama4.a4document.common.member.auth.jwt


import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


class JwtAuthenticationFilter() : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/**")) {

	override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {
		return request?.getHeader(HttpHeaders.AUTHORIZATION)
			?.let { this.authenticationManager.authenticate(JwtPreAuthenticationToken(it)) }
	}

	override fun successfulAuthentication(
		request: HttpServletRequest?,
		response: HttpServletResponse?,
		chain: FilterChain?,
		authResult: Authentication?
	) {
		super.successfulAuthentication(request, response, chain, authResult)
		chain?.doFilter(request, response)
	}

	override fun unsuccessfulAuthentication(
		request: HttpServletRequest?,
		response: HttpServletResponse?,
		failed: AuthenticationException?
	) {
		super.unsuccessfulAuthentication(request, response, failed)

	}
}