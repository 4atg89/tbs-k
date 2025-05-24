package com.atg.tbs.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileResponse(
    @SerialName("id") val id: String,
    @SerialName("nickname") val nickname: String,
    @SerialName("rating") val rating: Int,
    @SerialName("inventory") val inventory: ProfileInventoryResponse
)

@Serializable
class ProfileInventoryResponse(
    @SerialName("gems") val gems: Int,
    @SerialName("coins") val coins: Int,
)
