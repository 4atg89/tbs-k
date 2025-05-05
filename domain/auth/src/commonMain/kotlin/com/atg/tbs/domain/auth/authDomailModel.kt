package com.atg.tbs.domain.auth

import org.koin.dsl.module

fun authDomainModel() = module {
    single<AuthInteractor> { AuthInteractorImpl(get()) }
}