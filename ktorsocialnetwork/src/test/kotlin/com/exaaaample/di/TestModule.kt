package com.exaaaample.di

import com.exaaaample.data.repository.user.FakeUserRepository
import org.koin.dsl.module

internal val testModule = module {
    single { FakeUserRepository() }
}
