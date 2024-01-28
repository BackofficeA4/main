package com.teama4.a4document.system.exception

import com.teama4.a4document.common.member.auth.exception.AuthorMismatchException
import com.teama4.a4document.common.member.exception.DuplicateAccess
import com.teama4.a4document.common.member.exception.EmailNotFoundException
import com.teama4.a4document.common.member.exception.ModelNotFoundException
import com.teama4.a4document.common.member.exception.PasswordMismatchException
import com.teama4.a4document.system.errorobject.ErrorCode
import com.teama4.a4document.system.errorobject.ErrorObject
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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

	@ExceptionHandler(AuthorMismatchException::class)
	fun handleAuthorMismatchException(e: AuthorMismatchException): ResponseEntity<ErrorObject> {
		val errorCode = e.errorCode
		val errorObject = ErrorObject(errorCode.code, errorCode.message)
		return ResponseEntity.status(errorCode.statusCode).body(errorObject)
	}
	@ExceptionHandler(ModelNotFoundException::class)
	fun handleModelNotFoundException(e: EmailNotFoundException): ResponseEntity<ErrorObject> {
		val errorCode = e.errorCode
		val errorObject = ErrorObject(errorCode.code, errorCode.message)
		return ResponseEntity.status(errorCode.statusCode).body(errorObject)
	}

	//	@ExceptionHandler(EmailNotFoundException::class)
//	fun handleEmailNotFoundException(e: EmailNotFoundException): ResponseEntity<ErrorObject> {
//		val errorCode = e.errorCode
//		val errorObject = ErrorObject(errorCode.code, errorCode.message)
//		return ResponseEntity.status(errorCode.statusCode).body(errorObject)
//	}


	@ExceptionHandler(MethodArgumentNotValidException::class)
	fun dtoValidateError(error: MethodArgumentNotValidException): ResponseEntity<ErrorObject> {
		val errorMap = mutableMapOf<String, String>()

		error.bindingResult.fieldErrors.forEach {
			errorMap[it.field] = it.defaultMessage ?: "정의되지 않은 에러"
		}

		val errorObject = ErrorObject(ErrorCode.VALIDATION.code, ErrorCode.VALIDATION.message, payload = errorMap)

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorObject)
	}
}