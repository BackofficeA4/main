package com.teama4.a4document.directory.controller

import com.teama4.a4document.domain.dto.CreatePostRequest
import com.teama4.a4document.domain.post.service.PostService
import com.teama4.a4document.domain.dto.PostResponse
import com.teama4.a4document.domain.dto.UpdatePostRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/post")
@RestController

class PostController(
    private val postService: PostService

) {
    @GetMapping()
    fun getPostList(): ResponseEntity<List<PostResponse>> {
        // getpostlist 는  리스폰스 엔티티 안에 포스트 리스폰스를 리스트화 시켜서 사용할거다
        // 리스폰스 엔티티 는 포스트 서비스에있는 get all post list  값을 바디로 쓴다
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getAllPostList())
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long): ResponseEntity<PostResponse> {
        //getpost를 실행 시키기 위해서 () 안의 값이 필요하다
        // 리스폰스 엔티티는 서비스 안에있는 grtpostbyid값을 가진다
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostById(postId))
    }

    @PostMapping
    fun createPost(@RequestBody createPostRequest: CreatePostRequest): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.createPost(createPostRequest))
    }

    @PutMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody updateRequest: UpdatePostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(postId,updateRequest))
    }
    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Unit>
    {postService.deletePost(postId)
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build()

}}