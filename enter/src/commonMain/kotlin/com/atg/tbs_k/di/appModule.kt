package com.atg.tbs_k.di

import org.koin.dsl.module

fun appModule() = module {
    includes(splashModule)
}
