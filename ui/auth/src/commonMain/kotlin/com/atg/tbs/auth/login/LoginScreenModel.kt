package com.atg.tbs.auth.login

import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.common.SingleFlowEvent
import com.atg.tbs.domain.auth.auth.AuthInteractor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginScreenModel(private val interactor: AuthInteractor) : BaseScreenModel, BaseScreenModelImpl() {
    private val _f = SingleFlowEvent<String>()
    val f: SharedFlow<String> = _f.asSharedFlow()

    init {
        scope.launch {
            delay(5000)
            _f.tryEmit("aa")
            interactor.login("spankyhamtony@gmail.com4", "123qwe!Q")
        }
    }
}