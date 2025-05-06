package com.atg.tbs.account

interface AccountInteractor {

    suspend fun loadAccount()
}

class AccountInteractorImpl(private val repository: AccountRepository) : AccountInteractor {
    override suspend fun loadAccount() {
        repository.loadAccount()
    }
}