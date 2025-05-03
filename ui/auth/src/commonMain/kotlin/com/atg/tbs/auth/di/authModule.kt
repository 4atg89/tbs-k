package com.atg.tbs.auth.di

import com.atg.tbs.auth.login.LoginScreenModel
import org.koin.dsl.module

fun authModule() = module {
    factory { LoginScreenModel() }
}