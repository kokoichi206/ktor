package com.exaaaample.di

import com.exaaaample.repository.user.FakeUserRepository
import org.koin.dsl.module

internal val testModule = module {
    single { FakeUserRepository() }
}
