package com.atg.tbs.ui.events.di

import com.atg.tbs.data.events.eventsDataModule
import com.atg.tbs.domain.events.eventsDomainModule
import com.atg.tbs.ui.events.details.EventDetailsScreenModel
import com.atg.tbs.ui.events.overview.EventsScreenModel
import org.koin.dsl.module

fun eventsModule() = module {
    includes(eventsDataModule(), eventsDomainModule())
    factory { EventsScreenModel(get()) }
    factory { param -> EventDetailsScreenModel(param.get(), get()) }
}