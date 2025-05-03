package com.atg.tbs.auth.login

import androidx.compose.runtime.Immutable

data class LoginState(val showEmailError: Boolean = false, val showPasswordError: Boolean = false) {
    val canLogin get() = showEmailError.not() && showPasswordError.not()
}

@Immutable
data class LoginProps(
    val showEmailError: Boolean = false,
    val showPasswordError: Boolean = false,
    val loginBound: (String, String) -> Unit = { _, _ -> },
    val forgotPasswordBound: (String) -> Unit = {},
    val signUpBound: () -> Unit = {}
)