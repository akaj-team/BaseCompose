package com.ync.basecompose.data.network

import com.ync.basecompose.data.network.response.DetailCoinResponse
import com.ync.basecompose.data.network.response.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
interface Api {
    @GET("search/trending")
    suspend fun getTrending(): Response<TrendingResponse>

    @GET("coins/{id}")
    suspend fun getDetailCoin(@Path("id") id: String): Response<DetailCoinResponse>
}