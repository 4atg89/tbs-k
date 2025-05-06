package com.atg.tbs.domain.auth.password

interface PasswordInteractor {
    suspend fun passwordForgotten(email: String)
    suspend fun confirmChangePassword(code: String)
    suspend fun changePassword(password: String): String
}

internal class PasswordInteractorImpl(private val repository: PasswordRepository) : PasswordInteractor {

    override suspend fun passwordForgotten(email: String) {
        repository.passwordForgotten(email)
    }

    override suspend fun confirmChangePassword(code: String) {
        repository.confirmChangePassword(code)
    }

    override suspend fun changePassword(password: String): String {
        return repository.changePassword(password)
    }

}