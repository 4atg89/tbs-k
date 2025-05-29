package com.atg.tbs.data.account

import com.atg.tbs.account.ProfileRepository
import com.atg.tbs.account.model.ProfileChallengesEntity
import com.atg.tbs.account.model.ProfileClanEntity
import com.atg.tbs.account.model.ProfileEntity
import com.atg.tbs.account.model.ProfileInventoryEntity
import com.atg.tbs.account.model.ProfileStatisticsEntity
import com.atg.tbs.account.model.UserProfileDetailsEntity
import com.atg.tbs.account.model.UserProfileEntity
import com.atg.tbs.network.api.ProfileService
import com.atg.tbs.network.api.dto.ProfileChallengesResponse
import com.atg.tbs.network.api.dto.ProfileClanResponse
import com.atg.tbs.network.api.dto.ProfileInventoryResponse
import com.atg.tbs.network.api.dto.ProfileResponse
import com.atg.tbs.network.api.dto.ProfileStatisticsResponse

internal class ProfileRepositoryImpl(private val profileService: ProfileService) : ProfileRepository {

    override suspend fun loadProfile(): UserProfileEntity {
        return profileService.loadProfile().map()
    }

    override suspend fun loadProfileDetails(): UserProfileDetailsEntity {
        return profileService.loadProfileDetails().mapDetails()
    }

    private fun ProfileResponse.map() =
        UserProfileEntity(
            profile = ProfileEntity(id = id, nickname = nickname, rating = rating),
            inventory = inventory!!.map()
        )

    private fun ProfileResponse.mapDetails() =
        UserProfileDetailsEntity(
            profile = ProfileEntity(id = id, nickname = nickname, rating = rating),
            inventory = inventory!!.map(),
            clan = clan?.map(),
            statistics = statistics!!.map(),
            challenges = challenges!!.map(),
        )

    private fun ProfileInventoryResponse.map() =
         ProfileInventoryEntity(coins = coins, gems = gems)

    private fun ProfileClanResponse.map() =
         ProfileClanEntity(id = id, name = name)

    private fun ProfileStatisticsResponse.map() =
        ProfileStatisticsEntity(wins = wins, epicWins = epicWins, maxRating = maxRating)

    private fun ProfileChallengesResponse.map() =
         ProfileChallengesEntity(winStreak = winStreak, battlesCount = challengesCount)


}