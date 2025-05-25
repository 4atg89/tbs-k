package com.atg.tbs.account

import com.atg.tbs.account.model.UserProfileDetailsEntity
import com.atg.tbs.account.model.UserProfileEntity

interface ProfileInteractor {

    suspend fun loadProfile(): UserProfileEntity
    suspend fun loadProfileDetails(): UserProfileDetailsEntity
}

internal class ProfileInteractorImpl(private val repository: ProfileRepository) : ProfileInteractor {

    override suspend fun loadProfile(): UserProfileEntity {
       return repository.loadProfile()
    }

    override suspend fun loadProfileDetails(): UserProfileDetailsEntity {
       return repository.loadProfileDetails()
    }

}