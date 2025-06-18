package com.atg.tbs.domain.events

import com.atg.tbs.domain.events.model.EntryFeeType
import com.atg.tbs.domain.events.model.EventEntity

interface EventInteractor {
    suspend fun getEvents(): List<EventEntity>
    suspend fun getEvent(id: String): EventEntity
    suspend fun joinEvent(id: String, fee: EntryFeeType)
}

internal class EventInteractorImpl(private val repository: EventRepository) : EventInteractor {
    override suspend fun getEvents(): List<EventEntity> {
        return repository.getEvents()
    }

    override suspend fun getEvent(id: String): EventEntity {
        return repository.getEvent(id)
    }

    override suspend fun joinEvent(id: String, fee: EntryFeeType) {
        return repository.joinEvent(id, fee)
    }
}