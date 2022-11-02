package com.ync.basecompose.arch.extentions

import com.ync.basecompose.data.model.EmptyModel
import retrofit2.Response

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
inline fun <T, F> apiCall(
    block: () -> Response<T>
): F {
    val response = block()
    val body = response.body()
    return when (response.isSuccessful && body != null) {
        true -> (body as F) ?: (EmptyModel() as F)
        false -> throw response.toError()
    }
}