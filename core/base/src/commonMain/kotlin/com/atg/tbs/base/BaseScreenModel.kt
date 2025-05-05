package com.atg.tbs.base

import androidx.compose.runtime.MutableState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.atg.tbs.common.SingleFlowEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus

interface BaseScreenModel {
    val errorStream: MutableSharedFlow<Throwable>
    val scope: CoroutineScope
}

abstract class BaseScreenModelImpl : BaseScreenModel, ScreenModel {
    private val handler = CoroutineExceptionHandler { _, throwable ->
        //todo remove
        println("BaseScreenModelImpl $this $throwable")
        throwable.printStackTrace()
        errorStream.tryEmit(throwable)
    }

    override val errorStream = SingleFlowEvent<Throwable>()

    override val scope: CoroutineScope = screenModelScope + handler

    inline fun <reified T, reified R> Flow<T>.bind(
        props: MutableState<R>,
        crossinline transform: (T) -> R
    ) {
        onEach { props.value = transform(it) }.launchIn(scope)
    }
}
