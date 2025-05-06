package com.atg.tbs.ui.dashboard

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

object DashboardScreen : Screen {

    @Composable
    override fun Content() {
        val a = koinScreenModel<DashboardScreenModel>()
    }
}