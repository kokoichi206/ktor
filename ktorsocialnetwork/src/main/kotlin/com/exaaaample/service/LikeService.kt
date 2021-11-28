package com.exaaaample.service

import com.exaaaample.data.repository.likes.LikesRepository
import com.exaaaample.data.util.ParentType

class LikeService(
    private val repository: LikesRepository
) {

    suspend fun likeParent(userId: String, parentId: String, parentType: Int): Boolean {
        return repository.likeParent(userId, parentId, parentType)
    }

    suspend fun unlikeParent(userId: String, parentId: String): Boolean {
        return repository.unlikeParent(userId, parentId)
    }

    suspend fun deleteLikesForParent(parentId: String) {
        repository.deleteLikesForParent(parentId)
    }
}