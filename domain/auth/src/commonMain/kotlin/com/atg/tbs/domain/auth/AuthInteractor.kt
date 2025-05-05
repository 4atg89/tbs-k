package com.atg.tbs.domain.auth

interface AuthInteractor {
    suspend fun login(email: String, password: String)
}
internal class AuthInteractorImpl(private val repository: AuthRepository): AuthInteractor {
    override suspend fun login(email: String, password: String) {
        repository.login(email, password)
    }
}