package com.atg.tbs.domain.events

import com.atg.tbs.domain.events.model.EntryFeeType
import com.atg.tbs.domain.events.model.EventEntity

interface EventRepository {
    suspend fun getEvents(): List<EventEntity>
    suspend fun getEvent(id: String): EventEntity
    suspend fun joinEvent(id: String, fee: EntryFeeType)
}