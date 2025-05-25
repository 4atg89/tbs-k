package com.atg.tbs.ui.profile.di

import com.atg.tbs.account.accountModule
import com.atg.tbs.data.account.accountDataModule
import com.atg.tbs.ui.profile.ProfileScreenModel
import org.koin.dsl.module

fun profileModule() = module {
    includes(accountModule(), accountDataModule())
    factory { ProfileScreenModel(get()) }
}