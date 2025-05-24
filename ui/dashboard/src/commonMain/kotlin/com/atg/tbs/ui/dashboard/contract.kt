package com.atg.tbs.ui.dashboard

import com.atg.tbs.base.Effect

internal data class DashboardState(
    val currentScreen: DashboardScreensType = DashboardScreensType.BATTLE,
    val profile: ProfileApp? = null,
    val inventory: ProfileInventoryApp? = null
)

internal data class ProfileApp(
    val id: String,
    val nickname: String,
    val rating: Int,
)

internal data class ProfileInventoryApp(
    val coins: Int,
    val gems: Int
)

internal data class DashboardProps(
    val profile: ProfileApp? = null,
    val inventory: ProfileInventoryApp? = null,
    val openProfileBound: () -> Unit = {}
)

internal sealed interface DashboardRoute : Effect

internal data object ProfileRoute : DashboardRoute

internal enum class DashboardScreensType {
    MARKET, HEROES, BATTLE, CLAN, CHALLENGE
}