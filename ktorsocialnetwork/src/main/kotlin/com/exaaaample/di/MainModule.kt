package com.exaaaample.di

import com.exaaaample.repository.user.UserRepository
import com.exaaaample.repository.user.UserRepositoryImpl
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
}
