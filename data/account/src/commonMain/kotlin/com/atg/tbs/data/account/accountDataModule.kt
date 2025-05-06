package com.atg.tbs.data.account

import com.atg.tbs.account.AccountRepository
import org.koin.dsl.module

fun accountDataModule() = module {
    single<AccountRepository> { AccountRepositoryImpl(get()) }
}