package com.atg.tbs.auth.restore.password

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import com.atg.tbs.domain.auth.password.PasswordInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewPasswordScreenModel(
    private val interactor: PasswordInteractor
) : BaseScreenModel, BaseScreenModelImpl() {

    private val state = MutableStateFlow(NewPasswordState())
    val props = mutableStateOf(NewPasswordProps())
    val effect = SingleFlowEvent<Effect>()

    init { state.bind(props) { it.map() } }

    private fun NewPasswordState.map() = NewPasswordProps(
        backBound = ::back,
        newPasswordBound = ::newPassword
    )

    private fun back() {
        effect.tryEmit(BackRoute)
    }

    private fun newPassword(password: String) {
        scope.launch {
            interactor.changePassword(password)
            effect.tryEmit(ToLoginRoute)
        }
    }

}