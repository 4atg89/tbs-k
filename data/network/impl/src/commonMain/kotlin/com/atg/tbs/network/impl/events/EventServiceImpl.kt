package com.atg.tbs.network.impl.events

import com.atg.tbs.network.api.EventService
import com.atg.tbs.network.api.dto.EventResponse
import com.atg.tbs.network.api.dto.EventsResponse
import com.atg.tbs.network.impl.NetworkContract
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

internal class EventServiceImpl(private val ktor: HttpClient) : EventService {

    override suspend fun getEvents(): EventsResponse {
        return ktor.get(NetworkContract.Events.EVENTS).body<EventsResponse>()
    }

    override suspend fun getEvent(id: String): EventResponse {
        return ktor.get(NetworkContract.Events.event(id)).body<EventResponse>()
    }

    override suspend fun joinEvent(id: String) {
        ktor.post(NetworkContract.Events.joinEvent(id)).body<Unit>()
    }
} 