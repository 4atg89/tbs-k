package com.atg.tbs.data.auth

import com.atg.tbs.data.auth.auth.AuthRepositoryImpl
import com.atg.tbs.data.auth.password.PasswordRepositoryImpl
import com.atg.tbs.data.auth.session.SessionRepositoryImpl
import com.atg.tbs.domain.auth.auth.AuthRepository
import com.atg.tbs.domain.auth.password.PasswordRepository
import com.atg.tbs.domain.auth.session.SessionRepository
import org.koin.dsl.module

fun authDataModule() = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<PasswordRepository> { PasswordRepositoryImpl(get()) }
    single<SessionRepository> { SessionRepositoryImpl() }
}