package com.atg.tbs_k.di

import com.atg.tbs.network.impl.networkModule
import com.atg.tbs_k.EnterScreenModel
import org.koin.dsl.module

fun appModule() = module {
    //todo move networkModule() to upper module place out of enter project
    includes(splashModule, networkModule())

    factory { EnterScreenModel(get()) }
}
