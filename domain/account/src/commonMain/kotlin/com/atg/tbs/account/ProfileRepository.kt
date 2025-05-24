package com.atg.tbs.account

import com.atg.tbs.account.model.UserProfileEntity

interface ProfileRepository {
    suspend fun loadProfile(): UserProfileEntity
}
