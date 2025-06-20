package com.atg.tbs.domain.events

import org.koin.dsl.module

fun eventsDomainModule() = module {
    factory<EventInteractor> { EventInteractorImpl(get()) }
}