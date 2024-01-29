package com.teama4.a4document.system.exception

import com.teama4.a4document.system.errorobject.ErrorCode
class ModelNotFoundException (val errorCode: ErrorCode) : RuntimeException() {
}


