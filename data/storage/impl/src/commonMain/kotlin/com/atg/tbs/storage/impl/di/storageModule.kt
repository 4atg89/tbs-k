package com.atg.tbs.storage.impl.di

import com.atg.tbs.storage.impl.TokenStorageImpl
import com.atg.tbs.storage.api.TokenStorage
import com.atg.tbs.storage.impl.PlatformStorage
import org.koin.dsl.module

fun storageModule() = module {
    includes(storageExpectModule())
    single<PlatformStorage> { get<PlatformStorage>() }
    single<TokenStorage> { TokenStorageImpl(get()) }
}