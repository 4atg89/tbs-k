package com.atg.tbs.ui.events.overview

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import com.atg.tbs.domain.events.EventInteractor
import com.atg.tbs.domain.events.model.EventEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventsScreenModel(
    private val interactor: EventInteractor
) : BaseScreenModel, BaseScreenModelImpl() {

    private val state = MutableStateFlow(EventsState())
    val props = mutableStateOf(EventsProps())
    val effect = SingleFlowEvent<Effect>()

    init {
        state.bind(props) { it.map() }
        loadEvents()
    }

    private fun loadEvents() {
        scope.launch {
            state.value = state.value.copy(events = interactor.getEvents())
        }
    }

    private fun EventsState.map() = EventsProps(
        events = events,
        toDetails = ::openDetails
    )

    private fun openDetails(event: EventEntity) {
        effect.tryEmit(DetailsRoute(event))
    }
}