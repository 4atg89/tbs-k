package com.atg.tbs.ui.events.details

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import com.atg.tbs.domain.events.EventInteractor
import com.atg.tbs.domain.events.model.EntryFeeType
import com.atg.tbs.domain.events.model.EventEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EventDetailsScreenModel(
    private val event: EventEntity,
    private val interactor: EventInteractor
) : BaseScreenModel, BaseScreenModelImpl() {

    private val state = MutableStateFlow(EventDetailState(event = event))
    val props = mutableStateOf(EventDetailProps())

    val effect = SingleFlowEvent<Effect>()

    init {
        state.bind(props) { it.map() }
    }

    private fun EventDetailState.map() = EventDetailProps(
        event = event,
        joinEvent = ::joinEvent,
        syncEvent = ::syncEvent
    )

    private fun syncEvent() {
        scope.launch {
            interactor.getEvent(event.id)
        }
    }

    private fun joinEvent(fee: EntryFeeType) {
        scope.launch {
            interactor.joinEvent(event.id, fee)
        }
    }
}