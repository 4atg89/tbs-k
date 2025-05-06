package com.atg.tbs_k

import cafe.adriel.voyager.core.model.screenModelScope
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.domain.auth.session.SessionRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashScreenModel(private val sessionRepository: SessionRepository) : BaseScreenModel, BaseScreenModelImpl() {

    val state = MutableStateFlow(SplashState())

    init { isUserAuthenticated() }

    private fun isUserAuthenticated() {
        screenModelScope.launch {
            delay(5000)
            sessionRepository.token = null
        }
    }

}