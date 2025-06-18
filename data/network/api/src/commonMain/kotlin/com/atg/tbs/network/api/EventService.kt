package com.atg.tbs.network.api

import com.atg.tbs.network.api.dto.EventResponse
import com.atg.tbs.network.api.dto.EventsResponse

interface EventService {
    suspend fun getEvents(): EventsResponse
    suspend fun getEvent(id: String): EventResponse
    suspend fun joinEvent(id: String)
} 