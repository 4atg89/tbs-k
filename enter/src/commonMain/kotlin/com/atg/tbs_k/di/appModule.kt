package com.atg.tbs_k.di

import com.atg.tbs.network.impl.networkModule
import com.atg.tbs.storage.impl.di.storageModule
import com.atg.tbs.ui.dashboard.di.dashboardModule
import com.atg.tbs_k.EnterScreenModel
import org.koin.dsl.module

fun appModule() = module {
    //todo move networkModule() and storageModule() to upper module place out of enter project
    includes(splashModule, networkModule(), storageModule(), dashboardModule())

    factory { EnterScreenModel(get()) }
}
