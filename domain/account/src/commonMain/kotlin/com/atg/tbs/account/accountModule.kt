package com.atg.tbs.account

import org.koin.dsl.module

fun accountModule() = module {
    factory<ProfileInteractor> { ProfileInteractorImpl(get()) }
}