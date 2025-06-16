package com.atg.tbs.ui.events.overview

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow


class EventsScreenModel : BaseScreenModel, BaseScreenModelImpl() {

    private val state = MutableStateFlow(EventsState())
    val props = mutableStateOf(EventsProps())
    val effect = SingleFlowEvent<Effect>()

    init {
        state.bind(props) { it.map() }
        state.value = state.value.copy(events = List(4) {
            EventCardState(
                title = "Some event $it",
                backgroundUrl = "https://static.vecteezy.com/system/resources/previews/010/008/086/non_2x/background-dimension-3d-graphic-message-board-for-text-and-message-design-line-shadow-for-modern-web-design-free-vector.jpg",
                battleImage = "https://image.made-in-china.com/202f0j00DGveFyUnfTqk/Sekiro-Shadows-Die-Twice-Carbon-Steel-Red-Blade-Katana-Game-Sword.webp",
                description = "What? $it",
                prize = "10 cards and 20 coins",
                entryCost = "15 pogs"
            )
        })
    }

    private fun EventsState.map() = EventsProps(
        events = events,
        toDetails = ::openDetails
    )

    private fun openDetails(event: EventCardState) {
        effect.tryEmit(DetailsRoute(event))
    }
}