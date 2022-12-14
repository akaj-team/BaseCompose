package com.ync.basecompose.arch.extentions

import com.ync.basecompose.data.network.error.ErrorModel
import kotlinx.coroutines.flow.*

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
sealed class FlowResult<out T> {
    data class Success<T>(val value: T) : FlowResult<T>()
    data class Error(val error: ErrorModel) : FlowResult<Nothing>()
}

suspend inline fun <T> safeUseCase(
    crossinline block: suspend () -> T,
): FlowResult<T> = try {
    FlowResult.Success(block())
} catch (e: ErrorModel) {
    FlowResult.Error(e.toError())
}

inline fun <T> safeFlow(
    crossinline block: suspend () -> T,
): Flow<FlowResult<T>> = flow {
    try {
        val repoResult = block()
        emit(FlowResult.Success(repoResult))
    } catch (e: ErrorModel) {
        emit(FlowResult.Error(e))
    } catch (e: Exception) {
        e.printStackTrace()
        emit(FlowResult.Error(e.toError()))
    }
}

fun <T> observableFlow(block: suspend FlowCollector<T>.() -> Unit): Flow<FlowResult<T>> =
    flow(block)
        .catch { exception ->
            FlowResult.Error(exception.toError())
        }
        .map {
            FlowResult.Success(it)
        }

fun <T> Flow<FlowResult<T>>.onSuccess(action: suspend (T) -> Unit): Flow<FlowResult<T>> =
    transform { result ->
        if (result is FlowResult.Success<T>) {
            action(result.value)
        }
        return@transform emit(result)
    }

fun <T> Flow<FlowResult<T>>.mapSuccess(): Flow<T> =
    transform { result ->
        if (result is FlowResult.Success<T>) {
            emit(result.value)
        }
    }

fun <T> Flow<FlowResult<T>>.onError(
    normalAction: suspend (ErrorModel) -> Unit = {},
    commonAction: suspend (ErrorModel) -> Unit = {},
): Flow<FlowResult<T>> =
    transform { result ->
        if (result is FlowResult.Error) {
            if (result.error.isCommonError()) {
                commonAction(result.error)
            } else {
                normalAction(result.error)
            }
        }
        return@transform emit(result)
    }
