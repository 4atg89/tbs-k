package com.atg.tbs.network.api

import com.atg.tbs.network.api.dto.ProfileResponse

interface ProfileService {
    suspend fun loadProfile(): ProfileResponse
}