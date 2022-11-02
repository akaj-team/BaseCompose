package com.ync.basecompose.data.network

import com.ync.basecompose.data.network.response.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
interface Api {
    @GET("search/trending")
    suspend fun getTrending(): Response<TrendingResponse>
}