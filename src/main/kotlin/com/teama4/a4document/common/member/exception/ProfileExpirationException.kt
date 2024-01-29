package com.teama4.a4document.common.member.exception

import com.teama4.a4document.system.errorobject.ErrorCode

data class ProfileExpirationException(val errorCode: ErrorCode): RuntimeException()
