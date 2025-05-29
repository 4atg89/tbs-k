package com.atg.tbs.ui.dashboard.di

import com.atg.tbs.ui.heroes.HeroesScreenModel
import org.koin.dsl.module

fun heroesModule() = module {
    factory { HeroesScreenModel(get()) }
}