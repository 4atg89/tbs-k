package com.atg.tbs.auth.restore.password

import androidx.compose.runtime.Immutable
import com.atg.tbs.base.Effect

data class NewPasswordState(val isEmailValid: Boolean = false, val isPasswordValid: Boolean = false) {
    val canLogin get() = isEmailValid && isPasswordValid
}

@Immutable
data class NewPasswordProps(
    val newPasswordBound: (String) -> Unit = { },
    val backBound: () -> Unit = {}
)

sealed interface NewPasswordRoute : Effect

data object BackRoute : NewPasswordRoute
data object ToLoginRoute : NewPasswordRoute