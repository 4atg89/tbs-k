package com.atg.tbs.ui.feed.di

import com.atg.tbs.ui.feed.FeedScreenModel
import org.koin.dsl.module

fun feedModule() = module {
    factory { FeedScreenModel() }
}