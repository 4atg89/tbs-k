package com.atg.tbs.auth.verify

import androidx.compose.runtime.Immutable

data class VerifyState(val timeToExpire: Long = 0, val code: String = "")

@Immutable
data class VerifyProps(
    val code: String = "",
    val timeToExpire: Long = 0,
    val codeChangedBound: (String) -> Unit = {},
    val codeConfirmBound: (String, VerifyType) -> Unit = { _, _ -> },
    val resendBound: () -> Unit = {},
)