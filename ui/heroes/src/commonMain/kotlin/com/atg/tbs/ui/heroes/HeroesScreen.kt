package com.atg.tbs.ui.heroes

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object HeroesScreen : Screen {

    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<HeroesScreenModel>()
        val navigator = LocalNavigator.currentOrThrow
        Column {
            screenModel.props.value.heroes?.forEach {
                Text(text = "HeroesScreen hero -> ${it.id} ${it.level} ${it.nextLevelCoins} ${it.image} ${it.cardsQuantity}", fontWeight = FontWeight.Bold)
            }
        }
    }
}