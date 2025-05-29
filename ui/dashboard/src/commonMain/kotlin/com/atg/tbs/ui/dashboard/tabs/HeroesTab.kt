package com.atg.tbs.ui.dashboard.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.atg.tbs.ui.heroes.HeroesScreen
import com.atg.tbs.ui.market.MarketScreen

object HeroesTab : Tab {

    @Composable
    override fun Content() {
        Navigator(HeroesScreen)
    }

    override val options: TabOptions
        @Composable get() {

            return TabOptions(
                index = 1u,
                title = "Heroes",
                icon = rememberVectorPainter(Icons.Default.Check)
            )
        }
}