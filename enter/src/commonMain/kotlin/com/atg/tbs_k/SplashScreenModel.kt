package com.atg.tbs_k

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashScreenModel : ScreenModel {

    val state = MutableStateFlow(SplashState())

    fun process(action: SplashAction) = when (action) {
        is CheckAuthentication -> action.check()
    }

    private fun CheckAuthentication.check() {
        screenModelScope.launch {
            delay(2000)
            state.value = state.value.copy(isLogin = true)
        }
    }
}

sealed interface SplashAction

data object CheckAuthentication : SplashAction

data class SplashState(val isLogin: Boolean? = null)