package com.atg.tbs.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileResponse(
    @SerialName("id") val id: String,
    @SerialName("nickname") val nickname: String,
    @SerialName("rating") val rating: Int,
    @SerialName("inventory") val inventory: ProfileInventoryResponse?,
    @SerialName("clan") val clan: ProfileClanResponse?,
    @SerialName("mainStatistics") val statistics: ProfileStatisticsResponse?,
    @SerialName("challenges") val challenges: ProfileChallengesResponse?,
)

@Serializable
class ProfileInventoryResponse(
    @SerialName("gems") val gems: Int,
    @SerialName("coins") val coins: Int,
)

@Serializable
class ProfileClanResponse(
    @SerialName("id") val id: String,
    @SerialName("clanName") val name: String,
)

@Serializable
class ProfileStatisticsResponse(
    @SerialName("wins") val wins: Int,
    @SerialName("rating") val maxRating: Int,
    @SerialName("epicWins") val epicWins: Int,
)

@Serializable
class ProfileChallengesResponse(
    @SerialName("winStreak") val winStreak: Int,
    @SerialName("count") val battlesCount: Int,
)

