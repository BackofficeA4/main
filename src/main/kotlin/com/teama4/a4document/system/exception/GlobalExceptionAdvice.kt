package com.teama4.a4document.system.exception

import com.teama4.a4document.common.member.auth.exception.AuthorMismatchException
import com.teama4.a4document.common.member.exception.DuplicateAccess
import com.teama4.a4document.common.member.exception.EmailNotFoundException
import com.teama4.a4document.common.member.exception.PasswordMismatchException
import com.teama4.a4document.system.errorobject.ErrorObject
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionAdvice {
	@ExceptionHandler(DuplicateAccess::class)
	fun handleDuplicateAccess(e: DuplicateAccess): ResponseEntity<ErrorObject> {
		val errorCode = e.errorCode
		val errorObject = ErrorObject(errorCode.code, errorCode.message)
		return ResponseEntity.status(errorCode.statusCode).body(errorObject)
	}
	@ExceptionHandler(PasswordMismatchException::class)
	fun handlePasswordMismatchException(e: PasswordMismatchException): ResponseEntity<ErrorObject> {
		val errorCode = e.errorCode
		val errorObject = ErrorObject(errorCode.code, errorCode.message)
		return ResponseEntity.status(errorCode.statusCode).body(errorObject)
	}
	@ExceptionHandler(EmailNotFoundException::class)
	fun handleEmailNotFoundException(e: EmailNotFoundException): ResponseEntity<ErrorObject> {
		val errorCode = e.errorCode
		val errorObject = ErrorObject(errorCode.code, errorCode.message)
		return ResponseEntity.status(errorCode.statusCode).body(errorObject)
	}
	@ExceptionHandler(AuthorMismatchException::class)
	fun handleAuthorMismatchException(e: AuthorMismatchException): ResponseEntity<ErrorObject> {
		val errorCode = e.errorCode
		val errorObject = ErrorObject(errorCode.code, errorCode.message)
		return ResponseEntity.status(errorCode.statusCode).body(errorObject)
	}
}