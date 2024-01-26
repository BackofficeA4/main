package com.teama4.a4document.domain.post.service

import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.common.member.repository.MemberRepository
import com.teama4.a4document.domain.dto.CreatePostRequest
import com.teama4.a4document.domain.dto.PostResponse
import com.teama4.a4document.domain.dto.UpdatePostRequest
import com.teama4.a4document.domain.post.entity.PostEntity
import com.teama4.a4document.domain.post.repository.PostRepository
import com.teama4.a4document.infra.security.UserPrincipal
import jakarta.transaction.Transactional
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(
	private val postRepository: PostRepository,
) {

	fun getAllPostList(): List<PostResponse> {
		return postRepository.findAll().map { it.toResponse() }
	}

	fun getPostById(postId: Long): PostResponse {
		val post = postRepository.findByIdOrNull(postId) ?: throw Exception("error")
		return post.toResponse()
	}

	@Transactional
	fun createPost(request: CreatePostRequest, memberEntity: MemberEntity): PostResponse {
		return postRepository.save(
			PostEntity(
				contents = request.contents,
				title = request.title,
				member = memberEntity,
				anonymousNickname = RandomStringUtils.random(10, true, true)
			)
		).toResponse()
	}

	@Transactional
	fun updatePost(postId: Long, request: UpdatePostRequest, userPrincipal: UserPrincipal): PostResponse {
		val post = postRepository.findByIdOrNull(postId) ?: throw Exception("error.")
//		val (contents, title) = request

		post.contents = request.contents
		post.title = request.title

		return postRepository.save(post).toResponse()
	}

	@Transactional
	fun deletePost(postId: Long, userPrincipal: UserPrincipal) {
		val post = postRepository.findByIdOrNull(postId) ?: throw Exception("error.")
		postRepository.delete(post)
	}
}