package com.atg.tbs.network.impl.account

import com.atg.tbs.network.api.AccountService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AccountServiceImpl(private val ktor: HttpClient): AccountService {
    override suspend fun loadAccount() {
        ktor.get("weatherforecast").body<Unit>()
    }
}