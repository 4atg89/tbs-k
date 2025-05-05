package com.atg.tbs_k.di

import com.atg.tbs.auth.di.authModule
import com.atg.tbs_k.EnterScreenModel
import com.atg.tbs_k.SplashScreenModel
import org.koin.dsl.module

val splashModule = module {
    includes(authModule())
    factory { SplashScreenModel() }
}