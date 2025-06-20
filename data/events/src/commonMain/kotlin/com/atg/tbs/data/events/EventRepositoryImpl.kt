package com.atg.tbs.data.events

import com.atg.tbs.domain.events.EventRepository
import com.atg.tbs.domain.events.model.CardRewardEntity
import com.atg.tbs.domain.events.model.EntryFeeEntity
import com.atg.tbs.domain.events.model.EntryFeeType
import com.atg.tbs.domain.events.model.EventEntity
import com.atg.tbs.domain.events.model.EventType
import com.atg.tbs.domain.events.model.RewardEntity
import com.atg.tbs.network.api.EventService
import com.atg.tbs.network.api.dto.EventResponse
import kotlinx.datetime.Instant

internal class EventRepositoryImpl(private val eventService: EventService) : EventRepository {

    override suspend fun getEvents(): List<EventEntity> {
        //todo redo when server done
        return try {
            eventService.getEvents().events.map { it.toEventEntity() }
        } catch (e: Exception) {
            getMockEvents()
        }
    }

    override suspend fun getEvent(id: String): EventEntity {
        return eventService.getEvent(id).toEventEntity()
    }

    override suspend fun joinEvent(id: String, fee: EntryFeeType) {
        eventService.joinEvent(id)
    }

    private fun EventResponse.toEventEntity(): EventEntity {
        return EventEntity(
            id = id,
            title = title,
            banner = banner,
            description = description,
            info = info,
            startTime = Instant.parse(startTime),
            endTime = Instant.parse(endTime),
            reward = RewardEntity(
                gold = reward.gold,
                pogs = reward.pogs,
                cards = reward.cards.map { CardRewardEntity(it.cardId, it.amount) }
            ),
            winCount = winCount,
            lossCount = lossCount,
            iconUrl = iconUrl,
            eventType = EventType.ARENA_SURVIVAL,
            entryFees = entryFees.map { EntryFeeEntity(it.feeCount, EntryFeeType.POGS) }
        )
    }

    private fun getMockEvents(): List<EventEntity> {
        return listOf(
            EventEntity(
                id = "1",
                title = "Some Event 1",
                banner = "https://static.vecteezy.com/system/resources/previews/010/008/086/non_2x/background-dimension-3d-graphic-message-board-for-text-and-message-design-line-shadow-for-modern-web-design-free-vector.jpg",
                description = "Description of the event",
                info = "event information",
                startTime = Instant.parse("2025-06-18T18:43:00.50Z"),
                endTime = Instant.parse("2025-06-21T18:43:00.50Z"),
                reward = RewardEntity(
                    gold = 10,
                    pogs = 15,
                    cards = listOf(CardRewardEntity(cardId = "cardId", amount = 10))
                ),
                entryFees = listOf(EntryFeeEntity(feeCount = 10, type = EntryFeeType.POGS)),
                winCount = 3,
                lossCount = 2,
                iconUrl = "https://image.made-in-china.com/202f0j00DGveFyUnfTqk/Sekiro-Shadows-Die-Twice-Carbon-Steel-Red-Blade-Katana-Game-Sword.webp",
                eventType = EventType.ZONE_CONTROL
            ),
            EventEntity(
                id = "2",
                title = "Some Event 2",
                banner = "https://static.vecteezy.com/system/resources/previews/010/008/086/non_2x/background-dimension-3d-graphic-message-board-for-text-and-message-design-line-shadow-for-modern-web-design-free-vector.jpg",
                description = "Description of the event",
                info = "event information",
                startTime = Instant.parse("2025-03-18T18:43:00.50Z"),
                endTime = Instant.parse("2025-04-21T18:43:00.50Z"),
                reward = RewardEntity(
                    gold = 10,
                    pogs = 15,
                    cards = listOf(CardRewardEntity(cardId = "cardId", amount = 10))
                ),
                entryFees = listOf(EntryFeeEntity(feeCount = 10, type = EntryFeeType.COINS)),
                winCount = 3,
                lossCount = 2,
                iconUrl = "https://image.made-in-china.com/202f0j00DGveFyUnfTqk/Sekiro-Shadows-Die-Twice-Carbon-Steel-Red-Blade-Katana-Game-Sword.webp",
                eventType = EventType.RESOURCE_DEFENSE
            ),
            EventEntity(
                id = "3",
                title = "Some Event 3",
                banner = "https://static.vecteezy.com/system/resources/previews/010/008/086/non_2x/background-dimension-3d-graphic-message-board-for-text-and-message-design-line-shadow-for-modern-web-design-free-vector.jpg",
                description = "Description of the event",
                info = "event information",
                startTime = Instant.parse("2025-07-18T18:43:00.50Z"),
                endTime = Instant.parse("2025-08-21T18:43:00.50Z"),
                reward = RewardEntity(
                    gold = 10,
                    pogs = 15,
                    cards = listOf(CardRewardEntity(cardId = "cardId", amount = 10))
                ),
                entryFees = listOf(EntryFeeEntity(feeCount = 0, type = EntryFeeType.FREE)),
                winCount = 3,
                lossCount = 2,
                iconUrl = "https://image.made-in-china.com/202f0j00DGveFyUnfTqk/Sekiro-Shadows-Die-Twice-Carbon-Steel-Red-Blade-Katana-Game-Sword.webp",
                eventType = EventType.MULTI_CHANCE_TOURNAMENT
            ),
            EventEntity(
                id = "4",
                title = "Some Event 4",
                banner = "https://static.vecteezy.com/system/resources/previews/010/008/086/non_2x/background-dimension-3d-graphic-message-board-for-text-and-message-design-line-shadow-for-modern-web-design-free-vector.jpg",
                description = "Description of the event",
                info = "event information",
                startTime = Instant.parse("2025-06-11T18:43:00.50Z"),
                endTime = Instant.parse("2025-07-21T18:43:00.50Z"),
                reward = RewardEntity(
                    gold = 10,
                    pogs = 15,
                    cards = listOf(CardRewardEntity(cardId = "cardId", amount = 10))
                ),
                entryFees = listOf(EntryFeeEntity(feeCount = 0, type = EntryFeeType.ADS)),
                winCount = 3,
                lossCount = 2,
                iconUrl = "https://image.made-in-china.com/202f0j00DGveFyUnfTqk/Sekiro-Shadows-Die-Twice-Carbon-Steel-Red-Blade-Katana-Game-Sword.webp",
                eventType = EventType.ELIMINATION_TOURNAMENT
            ),
            EventEntity(
                id = "5",
                title = "Some Event 5",
                banner = "https://static.vecteezy.com/system/resources/previews/010/008/086/non_2x/background-dimension-3d-graphic-message-board-for-text-and-message-design-line-shadow-for-modern-web-design-free-vector.jpg",
                description = "Description of the event",
                info = "event information",
                startTime = Instant.parse("2025-03-18T18:43:00.50Z"),
                endTime = Instant.parse("2025-04-21T18:43:00.50Z"),
                reward = RewardEntity(
                    gold = 10,
                    pogs = 15,
                    cards = listOf(CardRewardEntity(cardId = "cardId", amount = 10))
                ),
                entryFees = listOf(
                    EntryFeeEntity(feeCount = 10, type = EntryFeeType.COINS),
                    EntryFeeEntity(feeCount = 11, type = EntryFeeType.POGS)
                ),
                winCount = 3,
                lossCount = 2,
                iconUrl = "https://image.made-in-china.com/202f0j00DGveFyUnfTqk/Sekiro-Shadows-Die-Twice-Carbon-Steel-Red-Blade-Katana-Game-Sword.webp",
                eventType = EventType.RESOURCE_DEFENSE
            ),
        )
    }
}