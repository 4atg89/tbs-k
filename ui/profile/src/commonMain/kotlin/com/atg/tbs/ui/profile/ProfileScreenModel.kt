package com.atg.tbs.ui.profile

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.account.ProfileInteractor
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class ProfileScreenModel (
    private val profileInteractor: ProfileInteractor
) : BaseScreenModel, BaseScreenModelImpl() {

    private val state = MutableStateFlow(ProfileState())
    val props = mutableStateOf(ProfileProps())
    val effect = SingleFlowEvent<Effect>()

    init {
        state.bind(props) { it.map() }
        scope.launch { state.value = state.value.copy(profile = profileInteractor.loadProfileDetails()) }
    }

    private fun ProfileState.map() = ProfileProps(
        profile = profile?.profile,
        inventory = profile?.inventory,
        clan = profile?.clan,
        statistics = profile?.statistics,
        challenges = profile?.challenges,
        backBound = ::goBack
    )

    private fun goBack() {
        effect.tryEmit(BackRoute)
    }
}