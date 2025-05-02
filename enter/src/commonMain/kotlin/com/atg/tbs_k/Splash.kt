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
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class Splash : Screen {

    @Composable
    override fun Content() {
        val model = rememberScreenModel { SplashScreenModel() }
        val state = model.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Splash", fontSize = 24.sp)
        }
        LaunchedEffect(Unit) { model.process(CheckAuthentication) }
        LaunchedEffect(state.value.isLogin) {

            when (state.value.isLogin) {
                true -> navigator.push(Example1())
                false -> navigator.push(Example2())
                else -> println("Waiting")
            }
        }
    }
}
