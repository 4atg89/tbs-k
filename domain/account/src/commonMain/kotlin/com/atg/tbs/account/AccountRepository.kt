package com.atg.tbs.account

interface AccountRepository {
    suspend fun loadAccount()
}
