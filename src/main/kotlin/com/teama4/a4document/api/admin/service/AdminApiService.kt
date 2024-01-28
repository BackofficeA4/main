package com.teama4.a4document.api.admin.service

import com.teama4.a4document.common.member.service.MemberService
import com.teama4.a4document.domain.post.comment.service.CommentService
import com.teama4.a4document.domain.post.service.PostService
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.stereotype.Service

@Service
class AdminApiService(
	private val memberService: MemberService,
	private val postService: PostService,
	private val commentService: CommentService,
) {
	fun deleteMember(userPrincipal: UserPrincipal, memberEmail: String) =
		memberService.deleteMember(userPrincipal, memberEmail)

	fun deletePost(postId: Long, userPrincipal: UserPrincipal) =
		postService.deletePost(postId, userPrincipal)


	fun deleteComment(commentId: Long, userPrincipal: UserPrincipal) =
		commentService.deleteComment(commentId, userPrincipal)

}