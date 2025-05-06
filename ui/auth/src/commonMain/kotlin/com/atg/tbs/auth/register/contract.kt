package com.atg.tbs.auth.register

import androidx.compose.runtime.Immutable
import com.atg.tbs.base.Effect

data class RegisterState(val isEmailValid: Boolean = false, val isPasswordValid: Boolean = false) {
    val canLogin get() = isEmailValid && isPasswordValid
}

@Immutable
data class RegisterProps(
    val showEmailError: Boolean = false,
    val showPasswordError: Boolean = false,
    val registerBound: (String, String, String) -> Unit = { _, _, _ -> },
    val backBound: () -> Unit = {}
)

sealed interface RegisterRoute : Effect

data object BackRoute : RegisterRoute
data object ConfirmRegisterRoute : RegisterRoute
