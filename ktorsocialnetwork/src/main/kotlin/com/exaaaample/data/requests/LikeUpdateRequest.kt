package com.exaaaample.data.requests

import com.exaaaample.data.util.ParentType

data class LikeUpdateRequest(
    val parentId: String,
    val parentType: Int
)
