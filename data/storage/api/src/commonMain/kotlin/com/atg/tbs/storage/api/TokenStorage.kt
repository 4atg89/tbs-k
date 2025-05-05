package com.atg.tbs.storage.api

interface TokenStorage {
    var token: String?
    var refreshToken: String?
}