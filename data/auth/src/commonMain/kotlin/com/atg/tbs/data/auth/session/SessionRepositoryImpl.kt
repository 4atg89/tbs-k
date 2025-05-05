package com.atg.tbs.data.auth.session

import com.atg.tbs.domain.auth.session.SessionRepository
import com.atg.tbs.storage.api.TokenStorage
import kotlinx.coroutines.flow.MutableStateFlow

class SessionRepositoryImpl(private val tokenStorage: TokenStorage) : SessionRepository {

    override val sessionOn = MutableStateFlow(false)

    override var token: String?
        get() = tokenStorage.token
        set(value) { tokenStorage.token = value }

    override var refreshToken: String?
        get() = tokenStorage.refreshToken
        set(value) { tokenStorage.refreshToken = value }

}