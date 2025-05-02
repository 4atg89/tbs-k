package com.atg.tbs_k

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@Composable
fun Entry() {
    Navigator(Enter())
}

class Enter : Screen {
    @Composable
    override fun Content() {
        KoinApplication(application = { modules(appModule()) }) {
            val navigator = LocalNavigator.currentOrThrow
            LaunchedEffect(Unit){
                navigator.push(Example1())
            }
            LaunchedEffect(Unit) {
                delay(5000)
                navigator.push(Example2())
            }
        }
    }

}

class Example1: Screen {

    @Composable
    override fun Content() {
//        val a = rememberScreenModel {  }
        val navigator = LocalNavigator.currentOrThrow
//        val viewModel = koinViewModel<EntryViewModel>()

        val screenModel = rememberScreenModel { ExampleScreenModel() }
        println("CoroutineScope Example1")
        Text(text = "Example1 from ${getPlatform().name}")
        LaunchedEffect(Unit) {
            screenModel.bla()
            delay(5000)
            navigator.push(Example2())
        }
    }
}



class Example2: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        println("CoroutineScope Example2")
        Text(text = "Example2 from ${getPlatform().name}")
        val screenModel = rememberScreenModel { ExampleScreenModel() }

//        val viewModel = koinViewModel<EntryViewModel>()
        LaunchedEffect(Unit) {
            screenModel.bla()
            delay(5000)
            navigator.pop()
        }
    }
}

class ExampleScreenModel : ScreenModel {

    init {
        println("CoroutineScope init -> ${screenModelScope.isActive}")
    }

    fun bla(){
        screenModelScope.launch {
            println("CoroutineScope bla -> ${screenModelScope.isActive} $this")
            delay(2000)
            println("CoroutineScope bla 2 -> ${screenModelScope.isActive} $this")
            delay(2000)
            println("CoroutineScope bla 3 -> ${screenModelScope.isActive} $this")
            delay(2000)
            println("CoroutineScope bla 4 -> ${screenModelScope.isActive} $this")
            delay(2000)
            println("CoroutineScope bla 5 -> ${screenModelScope.isActive} $this")
        }
    }

    override fun onDispose() {
        println("CoroutineScope onDispose1 -> ${screenModelScope.isActive}")
        super.onDispose()
        println("CoroutineScope onDispose1 -> ${screenModelScope.isActive}")
    }
}

fun appModule() = module {

}