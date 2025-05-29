package com.atg.tbs.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileResponse(
    @SerialName("id") val id: String,
    @SerialName("nickname") val nickname: String,
    @SerialName("rating") val rating: Int,
    @SerialName("inventory") val inventory: ProfileInventoryResponse?,
    @SerialName("heroes") val heroes: List<ProfileHeroesResponse>?,
    @SerialName("handHeroes") val handHeroes: List<ProfileHandHeroesResponse>?,
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
    @SerialName("maxRating") val maxRating: Int,
    @SerialName("epicWins") val epicWins: Int,
    @SerialName("gamesCount") val gamesCount: Int,
    @SerialName("killedEnemies") val killedEnemies: Int,
)

@Serializable
class ProfileChallengesResponse(
    @SerialName("winStreak") val winStreak: Int,
    @SerialName("challengesCount") val challengesCount: Int,
    @SerialName("challengesWins") val challengesWins: Int,
)

@Serializable
class ProfileHeroesResponse(
    @SerialName("heroId") val heroId: Int,
    @SerialName("level") val level: Int,
    @SerialName("cardsAmount") val cardsAmount: Int,
)

@Serializable
class ProfileHandHeroesResponse(
    @SerialName("heroId") val heroId: Int,
    @SerialName("handType") val handType: String
)

