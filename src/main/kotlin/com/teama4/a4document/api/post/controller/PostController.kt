package com.teama4.a4document.api.post.controller

import com.teama4.a4document.api.post.service.PostApiService
import com.teama4.a4document.domain.dto.CreatePostRequest
import com.teama4.a4document.domain.dto.PostResponse
import com.teama4.a4document.domain.dto.UpdatePostRequest
import com.teama4.a4document.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/post")
@RestController
class



PostController(
	private val postApiService: PostApiService
) {
	@GetMapping()
	fun getPostList(): ResponseEntity<List<PostResponse>> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(postApiService.getAllPostList())
	}

	@GetMapping("/{postId}")
	fun getPost(@PathVariable postId: Long): ResponseEntity<PostResponse> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(postApiService.getPostById(postId))
	}

	@PostMapping
	fun createPost(
		@RequestBody createPostRequest: CreatePostRequest,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<PostResponse> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(postApiService.createPost(createPostRequest, userPrincipal))
	}

	@PutMapping("/{postId}")
	fun updatePost(
		@PathVariable postId: Long,
		@RequestBody updateRequest: UpdatePostRequest,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<PostResponse> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(postApiService.updatePost(postId, updateRequest, userPrincipal))
	}

	@DeleteMapping("/{postId}")
	fun deletePost(
		@PathVariable postId: Long,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<Unit> {
		postApiService.deletePost(postId, userPrincipal)
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.build()

	}
}