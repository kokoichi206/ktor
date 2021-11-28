package com.exaaaample.data.repository.activity

import com.exaaaample.data.models.Activity
import com.exaaaample.util.Constants

interface ActivityRepository {

    suspend fun getActivitiesForUser(
        userId: String,
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_ACTIVITY_PAGE_SIZE
    ): List<Activity>

    suspend fun createActivity(activity: Activity)

    suspend fun deleteActivity(activity: Activity): Boolean
}
