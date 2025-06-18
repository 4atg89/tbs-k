package com.atg.tbs.ui.home.di

import com.atg.tbs.ui.home.HomeScreenModel
import org.koin.dsl.module

fun homeModule() = module {
    factory { HomeScreenModel() }
}