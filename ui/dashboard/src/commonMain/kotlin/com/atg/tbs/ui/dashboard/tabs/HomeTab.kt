package com.atg.tbs.ui.dashboard.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.atg.tbs.ui.home.HomeScreen

object HomeTab : Tab {

    @Composable
    override fun Content() {
        Navigator(HomeScreen)
    }

    override val options: TabOptions
        @Composable get() {

            return TabOptions(
                index = 2u,
                title = "Home",
                icon = rememberVectorPainter(Icons.Default.Home)
            )
        }
}