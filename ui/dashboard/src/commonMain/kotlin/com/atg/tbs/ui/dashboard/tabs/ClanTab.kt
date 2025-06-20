package com.atg.tbs.ui.dashboard.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Deck
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.atg.tbs.ui.clan.list.ClanListScreen

object ClanTab : Tab {

    @Composable
    override fun Content() {
        Navigator(ClanListScreen)
    }

    override val options: TabOptions
        @Composable get() {

            return TabOptions(
                index = 3u,
                title = "Clan",
                icon = rememberVectorPainter(Icons.Default.Deck)
            )
        }
}