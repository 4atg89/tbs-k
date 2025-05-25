package com.atg.tbs.account.model

class UserProfileEntity(val profile: ProfileEntity, val inventory: ProfileInventoryEntity)

class UserProfileDetailsEntity(
    val profile: ProfileEntity,
    val inventory: ProfileInventoryEntity,
    val statistics: ProfileStatisticsEntity,
    val clan: ProfileClanEntity?,
    val challenges: ProfileChallengesEntity,
)

class ProfileEntity(val id: String, val nickname: String, val rating: Int)

class ProfileInventoryEntity(val coins: Int, val gems: Int)

class ProfileStatisticsEntity(val wins: Int, val epicWins: Int, val maxRating: Int)

class ProfileClanEntity(val id: String, val name: String)

class ProfileChallengesEntity(val winStreak: Int, val battlesCount: Int)

