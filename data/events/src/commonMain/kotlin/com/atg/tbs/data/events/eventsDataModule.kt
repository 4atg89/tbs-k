package com.atg.tbs.data.events

import com.atg.tbs.domain.events.EventRepository
import org.koin.dsl.module

fun eventsDataModule() = module {
    single<EventRepository> { EventRepositoryImpl(get()) }
}