package com.atg.tbs.domain.events.model

import kotlinx.datetime.Instant

data class EventEntity(
    val id: String,
    val title: String,
    val banner: String,//?
    val description: String,
    val info: String,
    val startTime: Instant,
    val endTime: Instant,
    val reward: RewardEntity,
    val winCount: Int,
    val lossCount: Int,
    val iconUrl: String,
    val eventType: EventType,
    val entryFees: List<EntryFeeEntity>
)

data class EntryFeeEntity(
    val feeCount: Int,
    val type: EntryFeeType
)

enum class EntryFeeType{
    POGS, COINS, ADS, FREE
}
data class RewardEntity(
    val gold: Int,
    val pogs: Int,
    val cards: List<CardRewardEntity>,
) {
    val prize get() = "gold -> $gold | pogs -> $pogs | cards -> ${cards.sumOf { it.amount }}"
}

data class CardRewardEntity(
    val cardId: String,
    val amount: Int
)

enum class EventType {
    ARENA_SURVIVAL, //Fight against x different players in a row. One hero per player, survival-style. Win all to complete the event.
    ELIMINATION_TOURNAMENT,//Classic knockout tournament. 16 or 32 players. Lose once — you're out. Fight through brackets to become the champion.
    MULTI_CHANCE_TOURNAMENT,//Similar to classic tournament but allows multiple losses (e.g. 2–3). You're not out immediately — gives players second chances.
    ZONE_CONTROL,//Compete to capture and hold a control point. Fight against challengers to maintain dominance of the zone.
    RESOURCE_DEFENSE,//Defend your base while trying to conquer enemy bases. Mix of zone control and base protection mechanics.
}

