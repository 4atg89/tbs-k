package com.atg.tbs.ui.dashboard.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.atg.tbs.ui.market.MarketScreen

// Store or Market or Offers
object OffersTab : Tab {

    @Composable
    override fun Content() {
        Navigator(MarketScreen)
    }

    override val options: TabOptions
        @Composable get() {

            return TabOptions(
                index = 0u,
                title = "Market",
                icon = rememberVectorPainter(Icons.Default.Build)
            )
        }
}