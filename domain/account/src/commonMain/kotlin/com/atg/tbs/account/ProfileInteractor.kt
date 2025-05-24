package com.atg.tbs.account

import com.atg.tbs.account.model.UserProfileEntity

interface ProfileInteractor {

    suspend fun loadAccount(): UserProfileEntity
}

internal class ProfileInteractorImpl(private val repository: ProfileRepository) : ProfileInteractor {

    override suspend fun loadAccount(): UserProfileEntity {
       return repository.loadProfile()
    }

}