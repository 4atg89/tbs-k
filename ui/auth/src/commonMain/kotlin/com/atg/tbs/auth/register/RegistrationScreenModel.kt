package com.atg.tbs.auth.register

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import com.atg.tbs.domain.auth.auth.AuthInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegistrationScreenModel(private val interactor: AuthInteractor) : BaseScreenModel,
    BaseScreenModelImpl() {

    private val state = MutableStateFlow(RegisterState())
    val props = mutableStateOf(RegisterProps())
    val effect = SingleFlowEvent<Effect>()

    init { state.bind(props) { it.map() } }

    private fun RegisterState.map() = RegisterProps(
        showEmailError = isEmailValid.not(), showPasswordError = isPasswordValid.not(),
        backBound = ::toLogin,
        registerBound = ::register
    )

    private fun toLogin() {
        effect.tryEmit(BackRoute)
    }

    private fun register(nickname: String, email: String, password: String) {
        scope.launch {
            interactor.register(email = email, password = password, nickname = nickname)
            effect.tryEmit(ConfirmRegisterRoute)
        }
    }

}