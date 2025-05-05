package com.atg.tbs_k.android

import android.app.Application
import com.atg.tbs_k.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            //todo check if need to exclude
            androidLogger()
            modules(appModule())
        }
    }
}