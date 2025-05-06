package com.atg.tbs.auth.verify

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import com.atg.tbs.domain.auth.auth.AuthInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class VerifyScreenModel(private val authInteractor: AuthInteractor) : BaseScreenModel, BaseScreenModelImpl() {

    private val state = MutableStateFlow(VerifyState())
    val props = mutableStateOf(VerifyProps())
    val effect = SingleFlowEvent<Effect>()

    init {
        state.bind(props) { it.map() }
    }

    private fun VerifyState.map() = VerifyProps(
        code = code,
        timeToExpire = 0,
        codeChangedBound = {},
        codeConfirmBound = ::confirm,
        resendBound = {}
    )

    private fun confirm(code: String, type: VerifyType) {
        scope.launch {
            authInteractor.confirmLogin(code)
        }
    }
}