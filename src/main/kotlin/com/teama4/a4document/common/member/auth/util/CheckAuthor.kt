package com.teama4.a4document.common.member.auth.util

import com.teama4.a4document.common.member.auth.exception.AuthorMismatchException
import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.infra.security.UserPrincipal
import com.teama4.a4document.system.errorobject.ErrorCode
import org.springframework.security.core.GrantedAuthority

fun checkAuthor(userPrincipal: UserPrincipal, member: MemberEntity) {
    if(!userPrincipal.authorities.contains(GrantedAuthority { "ROLE_ADMIN" })) {
        if( userPrincipal.memberEmail != member.email )
            throw AuthorMismatchException(ErrorCode.MEMBER_MISMATCH_AUTHOR)
    }
}

