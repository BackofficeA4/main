package com.teama4.a4document.global.exception

import com.teama4.a4document.domain.exception.ForbiddenException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionAdvice {

	@ExceptionHandler
	fun test(forbiddenException: ForbiddenException): ResponseEntity<String> {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("hi")
	}

	@ExceptionHandler(SignatureException::class)
	fun test2(signatureException: SignatureException): ResponseEntity<String> {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("SignatureException")
	}

	@ExceptionHandler(MalformedJwtException::class)
	fun test3(malformedJwtException: MalformedJwtException): ResponseEntity<String> {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("MalformedJwtException")
	}
}