package com.atg.tbs.auth.login

import androidx.compose.runtime.Immutable
import com.atg.tbs.base.Effect

data class LoginState(val isEmailValid: Boolean = false, val isPasswordValid: Boolean = false) {
    val canLogin get() = isEmailValid && isPasswordValid
}

@Immutable
data class LoginProps(
    val showEmailError: Boolean = false,
    val showPasswordError: Boolean = false,
    val loginBound: (String, String) -> Unit = { _, _ -> },
    val forgotPasswordBound: (String) -> Unit = {},
    val signUpBound: () -> Unit = {}
)

sealed interface AuthRoute : Effect

data object SignInRoute : AuthRoute
data class ForgotPasswordRoute(val email: String) : AuthRoute
data object ConfirmLoginRoute : AuthRoute
