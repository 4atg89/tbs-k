package com.atg.tbs.storage.impl

import com.atg.tbs.storage.api.TokenStorage

internal class TokenStorageImpl(private val storage: PlatformStorage): TokenStorage {

    override var token: String?
        get() = storage.token
        set(value) { storage.token = value }

    override var refreshToken: String?
        get() = storage.refreshToken
        set(value) { storage.refreshToken = value }

}