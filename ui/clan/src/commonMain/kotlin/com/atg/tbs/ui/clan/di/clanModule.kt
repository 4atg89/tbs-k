package com.atg.tbs.ui.clan.di

import com.atg.tbs.ui.clan.details.ClanDetailsScreenModel
import com.atg.tbs.ui.clan.list.ClanListScreenModel
import org.koin.dsl.module

fun clanModule() = module {
    factory { ClanDetailsScreenModel() }
    factory { ClanListScreenModel() }
}