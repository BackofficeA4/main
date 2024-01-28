package com.teama4.a4document.common.member.exception

import com.teama4.a4document.system.errorobject.ErrorCode
class ModelNotFoundException (val errorCode: ErrorCode) : RuntimeException() {
}


