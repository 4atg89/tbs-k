package com.atg.tbs.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EventsResponse(
    @SerialName("events") val events: List<EventResponse>
)

@Serializable
class EventResponse(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("banner") val banner: String,
    @SerialName("description") val description: String,
    @SerialName("info") val info: String,
    @SerialName("startTime") val startTime: String,
    @SerialName("endTime") val endTime: String,
    @SerialName("reward") val reward: EventRewardResponse,
    @SerialName("entryFees") val entryFees: List<EntryFeeEntityResponse>,
    @SerialName("winCount") val winCount: Int,
    @SerialName("lossCount") val lossCount: Int,
    @SerialName("iconUrl") val iconUrl: String
)

@Serializable
class EntryFeeEntityResponse(
    val feeCount: Int,
    //todo later change to enum
    val type: String
)

@Serializable
class EventRewardResponse(
    @SerialName("gold") val gold: Int,
    @SerialName("pogs") val pogs: Int,
    @SerialName("cards") val cards: List<EventCardRewardResponse>
)

@Serializable
class EventCardRewardResponse(
    @SerialName("cardId") val cardId: String,
    @SerialName("amount") val amount: Int
) 