package com.atg.tbs.storage.impl

internal interface PlatformStorage {

    var token: String?
    var refreshToken: String?

    companion object {
        const val TOKEN_PREFS = "token_prefs"
        const val TOKEN_KEY = "TOKEN_KEY"
        const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY"
    }
}