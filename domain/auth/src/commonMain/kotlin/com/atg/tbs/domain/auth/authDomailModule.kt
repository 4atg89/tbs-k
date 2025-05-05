package com.atg.tbs.domain.auth

import com.atg.tbs.domain.auth.auth.AuthInteractor
import com.atg.tbs.domain.auth.auth.AuthInteractorImpl
import com.atg.tbs.domain.auth.password.PasswordInteractor
import com.atg.tbs.domain.auth.password.PasswordInteractorImpl
import com.atg.tbs.domain.auth.session.SessionInteractor
import com.atg.tbs.domain.auth.session.SessionInteractorImpl
import org.koin.dsl.module

fun authDomainModel() = module {
    // todo redo scope
    single<AuthInteractor> { AuthInteractorImpl(get()) }
    single<SessionInteractor> { SessionInteractorImpl(get()) }
    single<PasswordInteractor> { PasswordInteractorImpl(get()) }
}