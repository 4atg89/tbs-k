package com.atg.tbs_k

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.atg.tbs.auth.login.LoginScreen
import com.atg.tbs.auth.register.RegistrationScreen

class Splash : Screen {

    @Composable
    override fun Content() {
        val model = koinScreenModel<SplashScreenModel>()
        val state = model.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Splash", fontSize = 24.sp)
        }

        LaunchedEffect(state.value.isLogin) {

            when (state.value.isLogin) {
                true -> navigator.push(LoginScreen())
                false -> navigator.push(RegistrationScreen())
                else -> println("Waiting")
            }
        }
    }
}
