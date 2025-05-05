package com.atg.tbs.storage.impl.di

import com.atg.tbs.storage.impl.DesktopStorage
import com.atg.tbs.storage.impl.PlatformStorage
import org.koin.dsl.module

internal actual fun storageExpectModule() = module {
    single<PlatformStorage> { DesktopStorage() }
}