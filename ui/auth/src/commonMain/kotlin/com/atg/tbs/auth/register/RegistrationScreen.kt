package com.atg.tbs.auth.register

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

object RegistrationScreen: Screen {
    @Composable
    override fun Content() {
        val a = koinScreenModel<RegistrationScreenModel>()

    }
}