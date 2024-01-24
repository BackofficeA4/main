package com.teama4.a4document.infra.security.config

import com.teama4.a4document.common.member.auth.jwt.JwtAuthenticationFilter
import com.teama4.a4document.common.member.auth.jwt.JwtAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
	private val jwtAuthenticationProvider: JwtAuthenticationProvider,
) {

	@Bean
	fun securityConfig(http: HttpSecurity): DefaultSecurityFilterChain {
		return http
			.httpBasic { it.disable() }
			.formLogin { it.disable() }
			.csrf { it.disable() }
			.cors { it.disable() }
			.headers {
				it.frameOptions { frameOptionConfig -> frameOptionConfig.disable() }
			}
			.authorizeHttpRequests {
				it.requestMatchers(HttpMethod.GET, "/api/todo/**").permitAll()
				it.requestMatchers(
					"/api/auth/**",
					"/swagger-ui/**",
					"/v3/api-docs/**",
				).permitAll()
					.anyRequest().permitAll()
			}
			.addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
			.build()
	}

	@Bean
	fun authenticationManager(http: HttpSecurity): AuthenticationManager {
		return http.getSharedObject(AuthenticationManagerBuilder::class.java)
			.let {
				it.authenticationProvider(jwtAuthenticationProvider)
				it.build()
			}
	}
}