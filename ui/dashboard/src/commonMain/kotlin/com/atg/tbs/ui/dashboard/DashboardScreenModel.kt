package com.atg.tbs.ui.dashboard

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.account.ProfileInteractor
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class DashboardScreenModel(
    private val profileInteractor: ProfileInteractor
) : BaseScreenModel, BaseScreenModelImpl() {

    private val state = MutableStateFlow(DashboardState())
    val props = mutableStateOf(DashboardProps())
    val effect = SingleFlowEvent<Effect>()

    init {
        state.bind(props) { it.map() }
        scope.launch {
            state.value = profileInteractor.loadProfile()
                .let { state.value.copy(profile = it.profile, inventory = it.inventory) }
        }
    }

    private fun DashboardState.map() = DashboardProps(
        profile = profile,
        inventory = inventory,
        openProfileBound = ::openProfile,
    )

    private fun openProfile() {
        effect.tryEmit(ProfileRoute)
    }
}