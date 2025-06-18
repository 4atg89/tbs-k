package com.atg.tbs.ui.events.details

import com.atg.tbs.base.Effect
import com.atg.tbs.domain.events.model.EntryFeeType
import com.atg.tbs.domain.events.model.EventEntity

data class EventDetailState(
    val event: EventEntity? = null
)

data class EventDetailProps(
    val event: EventEntity? = null,
    val joinEvent: (EntryFeeType) -> Unit = {},
    val syncEvent: () -> Unit = {}
)

internal sealed interface EventDetailRoute : Effect

