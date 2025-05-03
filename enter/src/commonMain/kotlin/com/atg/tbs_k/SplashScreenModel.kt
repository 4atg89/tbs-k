package com.atg.tbs_k

import cafe.adriel.voyager.core.model.screenModelScope
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashScreenModel : BaseScreenModel, BaseScreenModelImpl() {

    val state = MutableStateFlow(SplashState())

    init { isUserAuthenticated() }

    private fun isUserAuthenticated() {
        screenModelScope.launch {
            delay(2000)
            state.value = state.value.copy(isLogin = true)
        }
    }

}