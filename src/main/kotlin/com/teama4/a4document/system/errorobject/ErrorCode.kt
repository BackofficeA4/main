package com.teama4.a4document.system.errorobject

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class ErrorCode(val code: Long, val message: String, val statusCode: HttpStatusCode) {
	POST(1001, "ID에 해당하는 POST를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
	POST_djwjrn(1002, "ID에 해당하는 POST를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),


	//

	COMMENT(2001, "어쩌구저쩌구", HttpStatus.NOT_FOUND)
}