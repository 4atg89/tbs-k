package com.atg.tbs.auth.verify

import androidx.compose.runtime.Immutable

data class VerifyState(val showEmailError: Boolean = false, val showPasswordError: Boolean = false) {
    val canLogin get() = showEmailError.not() && showPasswordError.not()
}

@Immutable
data class VerifyProps(
    val codeConfirmBound: (String) -> Unit = {},
)