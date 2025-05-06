package com.atg.tbs_k

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.Navigator
import com.atg.tbs.auth.login.LoginScreen
import com.atg.tbs.auth.register.RegistrationScreen

class EnterScreen : Screen {

    @Composable
    override fun Content() {

        val model = koinScreenModel<EnterScreenModel>()
        val state = model.state.collectAsState()

        val currentScreen by remember(state.value.isLogin) {
            derivedStateOf {
                when (state.value.isLogin) {
                    true -> SplashScreen
                    false -> LoginScreen
                    else -> SplashScreen
                }
            }
        }

        key(currentScreen) {
            Navigator(currentScreen)
        }
    }
}