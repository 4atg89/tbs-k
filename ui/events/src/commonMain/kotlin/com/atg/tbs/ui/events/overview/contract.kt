package com.atg.tbs.ui.events.overview

import com.atg.tbs.base.Effect

data class EventsState(
    val events: List<EventCardState> = emptyList()
)

data class EventsProps(
    val events: List<EventCardState> = emptyList(),
    val toDetails: (EventCardState) -> Unit = {}
)

data class EventCardState(
    val title: String = "",
    val backgroundUrl: String = "",
    val battleImage: String = "",
    val description: String = "",
    val prize: String = "",
    val entryCost: String = ""
)

internal sealed interface EventsRoute : Effect

data class DetailsRoute(val event: EventCardState): EventsRoute