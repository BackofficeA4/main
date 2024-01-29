package com.teama4.a4document.common.member.auth.exception

import com.teama4.a4document.system.errorobject.ErrorCode

class AuthorMismatchException(val errorCode: ErrorCode) : RuntimeException() {

}
