package com.teama4.a4document.system.errorobject

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class ErrorCode(val code: Long, val message: String, val statusCode: HttpStatusCode) {

	// Global Error
	MODEL_NOT_FOUND(10001, "정보가 존재하지 않습니다", HttpStatus.NOT_FOUND),
	VALIDATION(10002, "Validation을 통과하지 못했습니다.", HttpStatus.BAD_REQUEST),


	// Member Error
//	MEMBER_EMAIL_NOT_FOUND(3002, "이메일이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
	MEMBER_EMAIL_DUPLICATE(3003, "이미 사용중인 이메일입니다.", HttpStatus.CONFLICT),
	MEMBER_PASSWORD_MISMATCH(3004, "비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
//	MEMBER_VALIDATION(3005, "이메일 또는 비밀번호 형식이 맞지 않습니다.", HttpStatus.BAD_REQUEST)
	MEMBER_MISMATCH_AUTHOR(3006, "작성자가 아닙니다.", HttpStatus.FORBIDDEN)
}