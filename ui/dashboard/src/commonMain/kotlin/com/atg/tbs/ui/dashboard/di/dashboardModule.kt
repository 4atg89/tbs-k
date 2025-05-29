package com.atg.tbs.ui.dashboard.di

import com.atg.tbs.ui.dashboard.DashboardScreenModel
import org.koin.dsl.module
import com.atg.tbs.account.accountModule
import com.atg.tbs.data.account.accountDataModule
import com.atg.tbs.ui.profile.di.profileModule

fun dashboardModule() = module {
    includes(accountModule(), accountDataModule())
    includes(profileModule(), heroesModule())
    factory { DashboardScreenModel(get()) }
}