package com.atg.tbs.ui.events.di

import com.atg.tbs.ui.events.details.EventDetailsScreenModel
import com.atg.tbs.ui.events.overview.EventsScreenModel
import org.koin.dsl.module

fun eventsModule() = module {
    factory { EventsScreenModel() }
    factory { EventDetailsScreenModel() }
}