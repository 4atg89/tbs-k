package com.atg.tbs.ui.market

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen

object MarketScreen : Screen {
    @Composable
    override fun Content() {
        Text(text = "MarketScreen", fontWeight = FontWeight.Bold)
    }
}