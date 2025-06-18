package com.atg.tbs.ui.settings.di

import com.atg.tbs.ui.settings.SettingsScreenModel
import org.koin.dsl.module

fun settingsModule() = module {
    factory { SettingsScreenModel() }
}