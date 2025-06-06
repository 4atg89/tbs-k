package com.atg.tbs.network.impl

object NetworkContract {

    private const val API_V1 = "/api/v1"
    private const val API = "/api"

    object Auth {
        private const val AUTH = "$API_V1/auth"
        private const val PASSWORD = "$AUTH/password"
        const val REGISTER = "$AUTH/register"
        const val REGISTER_CONFIRMATION = "$REGISTER/confirm-code"
        const val LOGIN = "$AUTH/login"
        const val LOGIN_CONFIRMATION = "$LOGIN/confirm-code"
        const val REFRESH = "$AUTH/refresh"
        const val LOGOUT = "$AUTH/logout"
        const val PASSWORD_RESTORE = "$PASSWORD/restore"
        const val PASSWORD_CONFIRMATION = "$PASSWORD/confirm-code"
        const val PASSWORD_CHANGE = "$PASSWORD/change"
    }

    object Profile {
        const val PROFILE = "$API/profile"
        const val PROFILE_DETAILS = "$PROFILE/details"
    }
}
