package com.atg.tbs.network.impl

import com.atg.tbs.network.api.AuthService
import com.atg.tbs.network.impl.auth.AuthServiceImpl
import org.koin.dsl.module

fun networkModule() = module {
    single<AuthService> { AuthServiceImpl() }
}