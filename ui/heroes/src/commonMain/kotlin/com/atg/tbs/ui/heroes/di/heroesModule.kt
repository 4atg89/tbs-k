package com.atg.tbs.ui.heroes.di

import com.atg.tbs.ui.heroes.all.HeroesScreenModel
import com.atg.tbs.ui.heroes.detail.HeroScreenModel
import org.koin.dsl.module

fun heroesModule() = module {
    factory { HeroesScreenModel(get()) }
    factory { HeroScreenModel() }
}