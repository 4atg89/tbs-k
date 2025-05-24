package com.atg.tbs.account.model

class UserProfileEntity(val profile: ProfileEntity, val inventory: ProfileInventoryEntity)

class ProfileEntity(val id: String, val nickname: String, val rating: Int)

class ProfileInventoryEntity(val coins: Int, val gems: Int)