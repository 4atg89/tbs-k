package com.atg.tbs_k

import androidx.compose.ui.window.ComposeUIViewController
import com.atg.tbs_k.di.appModule
import org.koin.compose.KoinApplication

fun MainViewController() = ComposeUIViewController {
    //todo figure out why init works from here but not from init
    KoinApplication(application = { modules(appModule()) }) {
        EnterPoint()
    }
}