package com.atg.tbs.data.account

import com.atg.tbs.account.AccountRepository
import com.atg.tbs.network.api.AccountService
import kotlinx.coroutines.delay

class AccountRepositoryImpl(private val accountService: AccountService) : AccountRepository {

    override suspend fun loadAccount() {
        var a = 10
        while (a-- > 0){
            delay(15000)
            accountService.loadAccount()
        }
    }

}