package com.atg.tbs.auth.login

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import com.atg.tbs.domain.auth.auth.AuthInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenModel(private val interactor: AuthInteractor) : BaseScreenModel, BaseScreenModelImpl() {

    private val state = MutableStateFlow(LoginState())
    val props = mutableStateOf(LoginProps())
    val effect = SingleFlowEvent<Effect>()

    init { state.bind(props) { it.map() } }

    private fun LoginState.map() = LoginProps(
        showEmailError = isEmailValid.not(), showPasswordError = isPasswordValid.not(),
        loginBound = ::login,
        forgotPasswordBound = ::forgotPassword,
        signUpBound = ::signUp,
    )

    private fun login(email: String, password: String) {
        scope.launch {
            interactor.login(email, password)
            effect.tryEmit(ConfirmLoginRoute)
        }
    }

    private fun forgotPassword(email: String) {
        effect.tryEmit(ForgotPasswordRoute(email))
    }

    private fun signUp() {
        effect.tryEmit(RegisterRoute)
    }

}