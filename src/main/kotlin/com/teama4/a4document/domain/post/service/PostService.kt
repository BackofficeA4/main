package com.teama4.a4document.domain.post.service

import com.teama4.a4document.common.member.auth.util.checkAuthor
import com.teama4.a4document.common.member.entity.MemberEntity
import com.teama4.a4document.common.member.exception.ModelNotFoundException
import com.teama4.a4document.domain.post.dto.CreatePostRequest
import com.teama4.a4document.domain.post.dto.PostResponse
import com.teama4.a4document.domain.post.dto.UpdatePostRequest
import com.teama4.a4document.domain.post.entity.PostEntity
import com.teama4.a4document.domain.post.repository.PostRepository
import com.teama4.a4document.infra.security.UserPrincipal
import com.teama4.a4document.system.errorobject.ErrorCode
import jakarta.transaction.Transactional
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(
	private val postRepository: PostRepository
) {

	fun getAllPostList(): List<PostResponse> {
		return postRepository.findAll().map { it.toResponse() }
	}

	fun getPostById(postId: Long): PostResponse {
		val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException(ErrorCode.MODEL_NOT_FOUND)
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
		val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException(ErrorCode.MODEL_NOT_FOUND)

		checkAuthor(userPrincipal, post.member)

		post.contents = request.contents
		post.title = request.title

		return postRepository.save(post).toResponse()
	}

	@Transactional
	fun deletePost(postId: Long, userPrincipal: UserPrincipal) {
		val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException(ErrorCode.MODEL_NOT_FOUND)
		checkAuthor(userPrincipal, post.member)
		postRepository.delete(post)
	}
}