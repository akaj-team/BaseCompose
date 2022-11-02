@file:Suppress("PackageDirectoryMismatch")

package androidx.lifecycle

import com.ync.basecompose.arch.extentions.*
import kotlinx.coroutines.flow.*

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
private const val ERROR_FLOW_KEY = "androidx.lifecycle.ErrorFlow"
private const val LOADING_FLOW_KEY = "androidx.lifecycle.LoadingFlow"

suspend fun <T> T.emitErrorModel(throwable: Throwable) where T : ViewErrorAware, T : ViewModel {
    errorMutableSharedFlow.emit(throwable)
}

val <T> T.viewErrorFlow: SharedFlow<Throwable> where T : ViewErrorAware, T : ViewModel
    get() {
        return errorMutableSharedFlow
    }

val <T> T.loadingFlow: StateFlow<Boolean> where T : LoadingAware, T : ViewModel
    get() {
        return loadingMutableStateFlow
    }

var <T> T.isLoading: Boolean where T : LoadingAware, T : ViewModel
    get() {
        return loadingMutableStateFlow.value
    }
    set(value) {
        loadingMutableStateFlow.tryEmit(value)
    }

private val <T> T.loadingMutableStateFlow: MutableStateFlow<Boolean> where T : LoadingAware, T : ViewModel
    get() {
        val flow: MutableStateFlow<Boolean>? = getTag(LOADING_FLOW_KEY)
        return flow ?: setTagIfAbsent(LOADING_FLOW_KEY, MutableStateFlow(false))
    }


private val <T> T.errorMutableSharedFlow: MutableSharedFlow<Throwable> where T : ViewErrorAware, T : ViewModel
    get() {
        val flow: MutableSharedFlow<Throwable>? = getTag(ERROR_FLOW_KEY)
        return flow ?: setTagIfAbsent(ERROR_FLOW_KEY, MutableStateFlow(Throwable()))
    }

fun <F, T> Flow<FlowResult<F>>.bindLoading(t: T): Flow<FlowResult<F>> where T : LoadingAware, T : ViewModel {
    return this.onStart { t.loadingMutableStateFlow.value = true }
        .onCompletion { t.loadingMutableStateFlow.value = false }
}

fun <F, T> Flow<FlowResult<F>>.bindError(t: T): Flow<FlowResult<F>> where T : ViewErrorAware, T : ViewModel {
    return this.onError(
        normalAction = { t.emitErrorModel(it) },
        commonAction = { t.emitErrorModel(it) })
}

fun <F, T> Flow<FlowResult<F>>.bindCommonError(t: T): Flow<FlowResult<F>> where T : ViewErrorAware, T : ViewModel {
    return this.onError(commonAction = { t.emitErrorModel(it) })
}
