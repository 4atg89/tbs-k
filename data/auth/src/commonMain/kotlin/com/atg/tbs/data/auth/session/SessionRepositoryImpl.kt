package com.atg.tbs.data.auth.session

import com.atg.tbs.domain.auth.model.TokenEntity
import com.atg.tbs.domain.auth.session.SessionRepository
import com.atg.tbs.storage.api.TokenStorage
import kotlinx.coroutines.flow.MutableStateFlow

class SessionRepositoryImpl(private val tokenStorage: TokenStorage) : SessionRepository {

    override val sessionOn = MutableStateFlow(tokenStorage.getToken() != null)

    override var token: TokenEntity? = null
        get() = tokenStorage.getToken()
        set(value) { field = value.also { tokenStorage.setToken(it) } }

    private fun TokenStorage.setToken(model: TokenEntity?) {
        this@setToken.token = model?.token
        this@setToken.refreshToken = model?.refreshToken
        sessionOn.value = model != null
    }

    private fun TokenStorage.getToken(): TokenEntity? {
        val tokenLocal = this@getToken.token ?: return null
        val refreshTokenLocal = this@getToken.refreshToken ?: return null
        return TokenEntity(token = tokenLocal, refreshToken = refreshTokenLocal)
    }

}