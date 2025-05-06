package com.atg.tbs.auth.restore.byEmail

import androidx.compose.runtime.Immutable
import com.atg.tbs.base.Effect

data class ByEmailState(val isEmailValid: Boolean = false, val isPasswordValid: Boolean = false) {
    val canLogin get() = isEmailValid && isPasswordValid
}

@Immutable
data class ByEmailProps(
    val restoreBound: (String) -> Unit = { },
    val backBound: () -> Unit = {}
)

sealed interface ByEmailRoute : Effect

data object BackRoute : ByEmailRoute
data object ConfirmRestoreRoute : ByEmailRoute