package com.atg.tbs.ui.clan.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

data class ClanDetailsScreen(private val clanId: String) : Screen {

    @Composable
    override fun Content() {
        Text(text = "Clan id is $clanId")
    }

}