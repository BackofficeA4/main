package com.teama4.a4document.system.errorobject

data class ErrorObject(
	val code: Long,
	val message: String,
	val payload: Any? = null
)