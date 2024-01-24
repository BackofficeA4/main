package com.teama4.a4document.api.post.service

import com.teama4.a4document.common.member.repository.MemberRepository
import com.teama4.a4document.domain.dto.CreatePostRequest
import com.teama4.a4document.domain.dto.PostResponse
import com.teama4.a4document.domain.dto.UpdatePostRequest
import com.teama4.a4document.domain.post.comment.service.CommentService
import com.teama4.a4document.domain.post.service.PostService
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.stereotype.Service

@Service
class PostApiService(
	private val postService: PostService,
	private val memberRepository: MemberRepository,
	private val commentService: CommentService
) {
	fun getAllPostList(): List<PostResponse> {
		return postService.getAllPostList()
	}

	fun getPostById(postId: Long): PostResponse {
		return postService.getPostById(postId)
	}

	fun createPost(createPostRequest: CreatePostRequest, userPrincipal: UserPrincipal): PostResponse {
		return memberRepository.findByEmail(userPrincipal.memberEmail)
			?.let { postService.createPost(createPostRequest, it) }
			?: TODO("멤버 못찾음")
	}

	fun updatePost(postId: Long, updateRequest: UpdatePostRequest, userPrincipal: UserPrincipal): PostResponse {
		return postService.updatePost(postId, updateRequest, userPrincipal)
	}

	fun deletePost(postId: Long, userPrincipal: UserPrincipal) {
		return postService.deletePost(postId, userPrincipal)
	}
}