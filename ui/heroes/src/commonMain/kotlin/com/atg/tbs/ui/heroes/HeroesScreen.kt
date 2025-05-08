package com.atg.tbs.ui.heroes

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen

object HeroesScreen : Screen {

    @Composable
    override fun Content() {
        Text(text = "HeroesScreen", fontWeight = FontWeight.Bold)
    }
}