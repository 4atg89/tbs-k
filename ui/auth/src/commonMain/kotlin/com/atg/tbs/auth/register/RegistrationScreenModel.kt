package com.atg.tbs.auth.register

import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.domain.auth.session.SessionRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistrationScreenModel(private val sessionRepository: SessionRepository): BaseScreenModel, BaseScreenModelImpl() {

    init {
        scope.launch {
            delay(5000)
            sessionRepository.token = null
        }
    }

}