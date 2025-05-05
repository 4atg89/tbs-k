package com.atg.tbs_k

import com.atg.tbs_k.di.appModule
import org.koin.core.context.startKoin

fun InitKoinIos() {
    startKoin {
        modules(appModule())
    }
}