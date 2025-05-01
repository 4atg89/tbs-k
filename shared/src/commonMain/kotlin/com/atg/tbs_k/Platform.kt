package com.atg.tbs_k

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform