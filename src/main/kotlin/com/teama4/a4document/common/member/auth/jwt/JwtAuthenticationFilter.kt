package com.teama4.a4document.common.member.auth.jwt


import com.teama4.a4document.common.member.auth.jwt.token.JwtPreAuthenticationToken
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AndRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


class JwtAuthenticationFilter(
	authenticationManager: AuthenticationManager,
	private val jwtAuthenticationFailureHandler: JwtAuthenticationFailureHandler
) : AbstractAuthenticationProcessingFilter(
	AndRequestMatcher(
		AntPathRequestMatcher("/post/**"),
		AntPathRequestMatcher("/comment/**")
	), authenticationManager
) {

	override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication? {
		return request.getHeader(HttpHeaders.AUTHORIZATION)
			?.let { this.authenticationManager.authenticate(JwtPreAuthenticationToken(it.substring(6))) }
	}

	override fun unsuccessfulAuthentication(
		request: HttpServletRequest,
		response: HttpServletResponse,
		failed: AuthenticationException
	) {
		jwtAuthenticationFailureHandler.onAuthenticationFailure(request, response, failed)
	}
}