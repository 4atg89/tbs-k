package com.atg.tbs.data.account

import com.atg.tbs.account.ProfileRepository
import org.koin.dsl.module

fun accountDataModule() = module {
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
}