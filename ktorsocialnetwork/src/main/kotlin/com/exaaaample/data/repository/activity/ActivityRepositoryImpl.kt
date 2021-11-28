package com.exaaaample.data.repository.activity

import com.exaaaample.data.models.Activity
import com.exaaaample.data.models.Following
import com.exaaaample.data.models.Post
import org.litote.kmongo.`in`
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ActivityRepositoryImpl(
    db: CoroutineDatabase
): ActivityRepository {

    private val activities = db.getCollection<Activity>()

    override suspend fun getActivitiesForUser(
        userId: String,
        page: Int,
        pageSize: Int
    ): List<Activity> {
        return activities.find(Activity::toUserId eq userId)
            .skip(page * pageSize)
            .limit(pageSize)
            .descendingSort(Activity::timestamp)
            .toList()
    }

    override suspend fun createActivity(activity: Activity) {
        activities.insertOne(activity)
    }

    override suspend fun deleteActivity(activity: Activity): Boolean {
        TODO("Not yet implemented")
    }
}