package com.atg.tbs.domain.auth.auth

import com.atg.tbs.domain.auth.session.SessionRepository

interface AuthInteractor {

    suspend fun register(email: String, password: String, nickname: String)
    suspend fun confirmRegister(code: String)

    suspend fun login(email: String, password: String)
    suspend fun confirmLogin(code: String)

    suspend fun logout()

    suspend fun refresh()

}

internal class AuthInteractorImpl(
    private val repository: AuthRepository,
    private val sessionRepository: SessionRepository
): AuthInteractor {

    override suspend fun register(email: String, password: String, nickname: String) {
        repository.register(email = email, password = password, nickname = nickname)
    }

    override suspend fun confirmRegister(code: String) {
        sessionRepository.token = repository.confirmRegister(code)
    }

    override suspend fun login(email: String, password: String) {
        repository.login(email, password)
    }

    override suspend fun confirmLogin(code: String) {
        sessionRepository.token = repository.confirmLogin(code)
    }

    override suspend fun logout() {
        val refreshToken = requireNotNull(sessionRepository.token).refreshToken
        repository.logout(refreshToken)
    }

    override suspend fun refresh() {
        val refreshToken = requireNotNull(sessionRepository.token).refreshToken
        sessionRepository.token = repository.refresh(refreshToken)
    }
}