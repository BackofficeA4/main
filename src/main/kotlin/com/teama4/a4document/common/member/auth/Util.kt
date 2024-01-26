package com.teama4.a4document.common.member.auth

import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.common.member.exception.AuthorMismatchException
import com.teama4.a4document.system.errorobject.ErrorCode

fun checkAuthor(userEmail: String, member: MemberEntity) {
    if( userEmail != member.email ) throw AuthorMismatchException(ErrorCode.MEMBER_MISMATCH_AUTHOR)
}