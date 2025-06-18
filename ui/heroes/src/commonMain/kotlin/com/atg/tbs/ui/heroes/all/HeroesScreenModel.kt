package com.atg.tbs.ui.heroes.all

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.account.ProfileInteractor
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HeroesScreenModel(
    private val profileInteractor: ProfileInteractor
) : BaseScreenModel, BaseScreenModelImpl() {


    private val state = MutableStateFlow(HeroesState())
    internal val props = mutableStateOf(HeroesProps())
    val effect = SingleFlowEvent<Effect>()

    init {
        state.bind(props) { it.map() }
        scope.launch {
            state.value = profileInteractor.loadProfileDetails()
                .let { state.value.copy(heroes = it.heroes) }
        }
    }

    private fun HeroesState.map() = HeroesProps(
        heroes = heroes,
    )
}