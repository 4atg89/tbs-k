package com.atg.tbs_k

import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.domain.auth.session.SessionInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class EnterScreenModel(
    private val sessionInteractor: SessionInteractor
) : BaseScreenModel, BaseScreenModelImpl() {

    val state = MutableStateFlow(SplashState())

    init {
        sessionInteractor.sessionOn
            .onEach { state.value = state.value.copy(isLogin = it) }
            .launchIn(scope)
    }

}