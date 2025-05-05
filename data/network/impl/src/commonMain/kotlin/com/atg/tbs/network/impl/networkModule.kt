package com.atg.tbs.network.impl

import com.atg.tbs.network.api.AuthService
import com.atg.tbs.network.api.RestoreService
import com.atg.tbs.network.impl.auth.AuthServiceImpl
import com.atg.tbs.network.impl.auth.RestoreServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun networkModule() = module {
    single<AuthService> { AuthServiceImpl(get()) }
    single<RestoreService> { RestoreServiceImpl(get()) }
    single {
        val json = Json {
            explicitNulls = false
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
        HttpClient {
            //todo create via info provider and make only for debug
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 20000
                requestTimeoutMillis = 20000
            }
            install(ContentNegotiation) {
                json(json)

            }
            defaultRequest {
                url("http://$baseUrl:5030")
                contentType(ContentType.Application.Json)
            }
        }
    }
}