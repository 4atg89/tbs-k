package com.atg.tbs_k

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.atg.tbs_k.di.appModule
import org.koin.compose.KoinApplication

@Composable
fun EnterPoint(){
    KoinApplication(application = { modules(appModule()) }) {
        Navigator(EnterScreen())
    }
}