package com.atg.tbs.auth.restore.byEmail

import androidx.compose.runtime.mutableStateOf
import com.atg.tbs.base.BaseScreenModel
import com.atg.tbs.base.BaseScreenModelImpl
import com.atg.tbs.base.Effect
import com.atg.tbs.common.SingleFlowEvent
import com.atg.tbs.domain.auth.password.PasswordInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ByEmailScreenModel(
    private val interactor: PasswordInteractor
) : BaseScreenModel, BaseScreenModelImpl() {

    private val state = MutableStateFlow(ByEmailState())
    val props = mutableStateOf(ByEmailProps())
    val effect = SingleFlowEvent<Effect>()

    init {
        state.bind(props) { it.map() }
    }

    private fun ByEmailState.map() = ByEmailProps(
        backBound = ::toLogin,
        restoreBound = ::restore
    )

    private fun restore(email: String) {
        scope.launch {
            interactor.passwordForgotten(email)
            effect.tryEmit(ConfirmRestoreRoute)
        }
    }

    private fun toLogin() {
        effect.tryEmit(BackRoute)
    }

}