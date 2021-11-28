package com.exaaaample.data.repository.likes

import com.exaaaample.data.models.Like
import com.exaaaample.data.models.User
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class LikesRepositoryImpl(
    db: CoroutineDatabase
) : LikesRepository {

    private val likes = db.getCollection<Like>()
    private val users = db.getCollection<User>()

    override suspend fun likeParent(userId: String, parentId: String): Boolean {
        val doesUerExist = users.findOneById(userId) != null
        return if (doesUerExist) {
            likes.insertOne(Like(userId, parentId))
            true
        } else {
            false
        }
    }

    override suspend fun unlikeParent(userId: String, parentId: String): Boolean {
        val doesUerExist = users.findOneById(userId) != null
        return if (doesUerExist) {
            likes.deleteOne(
                and(
                    Like::userId eq userId,
                    Like::parentId eq parentId
                )
            )
            true
        } else {
            false
        }
    }

    override suspend fun deleteLikesForParent(parentId: String) {
        likes.deleteMany(Like::parentId eq parentId)
    }
}