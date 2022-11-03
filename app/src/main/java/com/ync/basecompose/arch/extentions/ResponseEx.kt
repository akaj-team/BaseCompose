package com.ync.basecompose.arch.extentions

import com.ync.basecompose.data.network.error.ErrorModel
import com.ync.basecompose.data.network.response.ErrorResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
inline fun <T, R> Response<T>.mapSuccess(
    crossinline block: (T) -> R,
): R {
    val safeBody = body()
    if (this.isSuccessful && safeBody != null) {
        return block(safeBody)
    } else {
        throw toError()
    }
}

private val json = Json { ignoreUnknownKeys = true }

fun <T> Response<T>.toError(): ErrorModel.Http {
    val error = json.decodeFromString<ErrorResponse>(errorBody()?.string() ?: "")
    val message = error.error
    return ErrorModel.Http.ApiError(
        code = code().toString(),
        message = message
            ?: ErrorModel.LocalErrorException.UN_KNOW_EXCEPTION.message,
        apiUrl = this.raw().request.url.toString()
    )
}

fun Throwable.toError(): ErrorModel {
    return when (this) {
        is SocketTimeoutException -> ErrorModel.LocalError(
            ErrorModel.LocalErrorException.REQUEST_TIME_OUT_EXCEPTION.message,
            ErrorModel.LocalErrorException.REQUEST_TIME_OUT_EXCEPTION.code
        )
        is UnknownHostException -> ErrorModel.LocalError(
            ErrorModel.LocalErrorException.NO_INTERNET_EXCEPTION.message,
            ErrorModel.LocalErrorException.NO_INTERNET_EXCEPTION.code
        )
        is ConnectException -> ErrorModel.LocalError(
            ErrorModel.LocalErrorException.NO_INTERNET_EXCEPTION.message,
            ErrorModel.LocalErrorException.NO_INTERNET_EXCEPTION.code
        )
        else -> ErrorModel.LocalError(
            ErrorModel.LocalErrorException.UN_KNOW_EXCEPTION.message,
            "1014"
        )
    }
}
