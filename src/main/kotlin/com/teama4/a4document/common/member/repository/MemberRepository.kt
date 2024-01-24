package com.teama4.a4document.common.member.repository

import com.teama4.a4document.common.member.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): MemberEntity?
}