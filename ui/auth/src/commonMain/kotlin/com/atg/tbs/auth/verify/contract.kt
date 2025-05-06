package com.atg.tbs.auth.verify

import androidx.compose.runtime.Immutable
import com.atg.tbs.base.Effect

data class VerifyState(val timeToExpire: Long = 0, val code: String = "")

@Immutable
data class VerifyProps(
    val code: String = "",
    val timeToExpire: Long = 0,
    val codeChangedBound: (String) -> Unit = {},
    val codeConfirmBound: (String) -> Unit = { },
    val resendBound: () -> Unit = {},
)


sealed interface VerifyRoute : Effect

data object BackRoute : VerifyRoute
data object ChangePasswordRoute : VerifyRoute
