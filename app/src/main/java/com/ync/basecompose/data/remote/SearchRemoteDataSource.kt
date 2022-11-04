package com.ync.basecompose.data.remote

import com.ync.basecompose.arch.extentions.apiCall
import com.ync.basecompose.data.network.Api
import com.ync.basecompose.data.network.response.DetailCoinResponse
import com.ync.basecompose.data.network.response.TrendingResponse
import javax.inject.Inject

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
class SearchRemoteDataSource @Inject constructor(
    private val api: Api,
) {
    suspend fun getTrending(): TrendingResponse = apiCall {
        api.getTrending()
    }

    suspend fun getDetailCoin(id: String): DetailCoinResponse = apiCall {
        api.getDetailCoin(id)
    }
}
