package com.atg.tbs.network.impl

import com.atg.tbs.domain.auth.model.TokenEntity
import com.atg.tbs.domain.auth.session.SessionRepository
import com.atg.tbs.network.api.AccountService
import com.atg.tbs.network.api.AuthService
import com.atg.tbs.network.api.RestoreService
import com.atg.tbs.network.api.dto.AuthenticatedResponse
import com.atg.tbs.network.api.dto.RefreshTokenRequest
import com.atg.tbs.network.impl.account.AccountServiceImpl
import com.atg.tbs.network.impl.auth.AuthServiceImpl
import com.atg.tbs.network.impl.auth.RestoreServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.RefreshTokensParams
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun networkModule() = module {
    single<AuthService> { AuthServiceImpl(get()) }
    single<RestoreService> { RestoreServiceImpl(get()) }
    single<AccountService> { AccountServiceImpl(get()) }
    single {
        HttpClient {
            //todo create via info provider and make only for debug
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(Auth) {
                bearer {
                    loadTokens { get<SessionRepository>().token?.toBearer() }
                    refreshTokens {
                        with(get<SessionRepository>()) {
                            token?.refreshToken?.let { refreshToken -> fetchNewToken(refreshToken) }
                                .also { token = it?.toTokenEntity() }
                        }
                    }
                }
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 20000
                requestTimeoutMillis = 20000
            }
            install(ContentNegotiation) {
                json(Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    isLenient = true
                    coerceInputValues = true
                })
            }
            defaultRequest {
                url("http://$baseUrl:5030")
                contentType(ContentType.Application.Json)
            }
        }
    }
}

private fun TokenEntity.toBearer(): BearerTokens =
    BearerTokens(token, refreshToken)

private fun BearerTokens.toTokenEntity(): TokenEntity =
    TokenEntity(accessToken, refreshToken)

private suspend fun RefreshTokensParams.fetchNewToken(refreshToken: String) = runCatching {
    client.post(NetworkContract.Auth.REFRESH) {
        setBody(RefreshTokenRequest(refreshToken))
        markAsRefreshTokenRequest()
    }.body<AuthenticatedResponse>().let { BearerTokens(it.token, it.refreshToken) }
}.getOrDefault(null)
