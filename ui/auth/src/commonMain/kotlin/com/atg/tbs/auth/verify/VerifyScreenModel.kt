package com.atg.tbs.auth.verify

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import com.atg.tbs.domain.auth.auth.AuthInteractor
import com.atg.tbs.domain.auth.password.PasswordInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class VerifyScreenModel(
    private val type: VerifyType,
    private val authInteractor: AuthInteractor,
    private val passwordInteractor: PasswordInteractor
) : BaseScreenModel, BaseScreenModelImpl() {

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

    private fun confirm(code: String) {
        scope.launch {
            when(type){
                VerifyType.LOGIN -> authInteractor.confirmLogin(code)
                VerifyType.REGISTER -> authInteractor.confirmRegister(code)
                VerifyType.RESTORE_PASSWORD -> passwordInteractor.confirmPasswordCode(code)
            }
        }
    }

    private suspend fun PasswordInteractor.confirmPasswordCode(code: String){
        confirmChangePassword(code)
        effect.tryEmit(ChangePasswordRoute)
    }
}