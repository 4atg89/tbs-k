package com.atg.tbs.ui.dashboard

import com.atg.tbs.account.model.ProfileEntity
import com.atg.tbs.account.model.ProfileInventoryEntity
import com.atg.tbs.base.Effect

internal data class DashboardState(
    val currentScreen: DashboardScreensType = DashboardScreensType.BATTLE,
    val profile: ProfileEntity? = null,
    val inventory: ProfileInventoryEntity? = null
)

internal data class DashboardProps(
    val profile: ProfileEntity? = null,
    val inventory: ProfileInventoryEntity? = null,
    val openProfileBound: () -> Unit = {}
)

internal sealed interface DashboardRoute : Effect

internal data object ProfileRoute : DashboardRoute

internal enum class DashboardScreensType {
    MARKET, HEROES, BATTLE, CLAN, EVENTS
}