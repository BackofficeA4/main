package com.teama4.a4document.common.member.exception

import com.teama4.a4document.system.errorobject.ErrorCode

class EmailNotFoundException(val errorCode: ErrorCode) : RuntimeException() {

}
