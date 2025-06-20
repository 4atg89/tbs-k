package com.atg.tbs.ui.events.overview

import com.atg.tbs.base.Effect
import com.atg.tbs.domain.events.model.EventEntity

data class EventsState(
    val events: List<EventEntity> = emptyList()
)

data class EventsProps(
    val events: List<EventEntity> = emptyList(),
    val toDetails: (EventEntity) -> Unit = {}
)

internal sealed interface EventsRoute : Effect

data class DetailsRoute(val event: EventEntity): EventsRoute