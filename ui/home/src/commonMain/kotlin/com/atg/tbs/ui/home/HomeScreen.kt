package com.atg.tbs.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen

object HomeScreen: Screen {

    @Composable
    override fun Content() {
        Text(text = "HomeScreen", fontWeight = FontWeight.Bold)
    }
}