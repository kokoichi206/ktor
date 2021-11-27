package com.exaaaample.di

import com.exaaaample.data.repository.follow.FollowRepository
import com.exaaaample.data.repository.follow.FollowRepositoryImpl
import com.exaaaample.data.repository.post.PostRepository
import com.exaaaample.data.repository.post.PostRepositoryImpl
import com.exaaaample.data.repository.user.UserRepository
import com.exaaaample.data.repository.user.UserRepositoryImpl
import com.exaaaample.service.FollowService
import com.exaaaample.service.PostService
import com.exaaaample.service.UserService
import com.exaaaample.util.Constants
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val mainModule = module {
    single {
        val client = KMongo.createClient().coroutine
        client.getDatabase(Constants.DATABASE_NAME)
    }
    single<UserRepository?> {
        UserRepositoryImpl(get())
    }
    single<FollowRepository?> {
        FollowRepositoryImpl(get())
    }
    single<PostRepository?> {
        PostRepositoryImpl(get())
    }
    single { UserService(get()) }
    single { FollowService(get()) }
    single { PostService(get()) }
}
