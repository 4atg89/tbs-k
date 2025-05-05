package com.atg.tbs.data.auth.password

import com.atg.tbs.network.api.dto.CodeExpirationResponse

internal class PasswordModel(
    var email: String? = null,
    var resetToken: String? = null,
    var codeModel: CodeExpirationResponse? = null
)