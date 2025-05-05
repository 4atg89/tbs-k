package com.atg.tbs.data.auth

import com.atg.tbs.domain.auth.AuthRepository
import org.koin.dsl.module

fun authDataModule() = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}