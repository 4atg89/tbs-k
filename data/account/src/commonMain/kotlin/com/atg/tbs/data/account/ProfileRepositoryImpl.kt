package com.atg.tbs.data.account

import com.atg.tbs.account.ProfileRepository
import com.atg.tbs.account.model.ProfileEntity
import com.atg.tbs.account.model.ProfileInventoryEntity
import com.atg.tbs.account.model.UserProfileEntity
import com.atg.tbs.network.api.ProfileService
import com.atg.tbs.network.api.dto.ProfileResponse

class ProfileRepositoryImpl(private val profileService: ProfileService) : ProfileRepository {

    override suspend fun loadProfile(): UserProfileEntity {
        return profileService.loadProfile().map()
    }

    private fun ProfileResponse.map() =
        UserProfileEntity(
            profile = ProfileEntity(id = id, nickname = nickname, rating = rating),
            inventory = ProfileInventoryEntity(coins = inventory.coins, gems = inventory.gems)
        )

}