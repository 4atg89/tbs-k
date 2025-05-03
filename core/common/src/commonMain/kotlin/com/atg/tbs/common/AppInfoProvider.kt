package com.atg.tbs.common

interface AppInfoProvider {
    val isDebug: Boolean
    val baseUrl: String
    val appName: String
    val platform: String
}