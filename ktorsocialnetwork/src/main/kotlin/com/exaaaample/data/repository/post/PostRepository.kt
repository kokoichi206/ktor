package com.exaaaample.data.repository.post

import com.exaaaample.data.models.Post
import com.exaaaample.util.Constants

interface PostRepository {

    suspend fun createPost(post: Post): Boolean

    suspend fun deletePost(postId: String)

    suspend fun getPostsByFollows(
        userId: String,
        page: Int,
        pageSize: Int = Constants.DEFAULT_POST_PAGE_SIZE
    ): List<Post>

    suspend fun getPostsForProfile(
        userId: String,
        page: Int,
        pageSize: Int = Constants.DEFAULT_POST_PAGE_SIZE
    ): List<Post>

    suspend fun getPost(postId: String): Post?
}