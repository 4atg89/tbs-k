package com.atg.tbs.domain.auth.auth

import com.atg.tbs.domain.auth.session.SessionRepository

interface AuthInteractor {

    suspend fun login(email: String, password: String)
    suspend fun confirmLogin(code: String)

}

internal class AuthInteractorImpl(
    private val repository: AuthRepository,
    private val sessionRepository: SessionRepository
): AuthInteractor {

    override suspend fun login(email: String, password: String) {
        repository.login(email, password)
    }

    override suspend fun confirmLogin(code: String) {
        val model = repository.confirmLogin(code)
        sessionRepository.token = model.token
        sessionRepository.refreshToken = model.refreshToken
    }
}