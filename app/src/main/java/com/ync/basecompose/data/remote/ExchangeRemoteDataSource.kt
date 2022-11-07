package com.ync.basecompose.data.remote

import com.ync.basecompose.arch.extentions.apiCall
import com.ync.basecompose.data.model.Exchanges
import com.ync.basecompose.data.network.Api
import javax.inject.Inject

/**
 * @author mvn-toan.nguyen2 on 11/4/22
 **/
class ExchangeRemoteDataSource @Inject constructor(
    private val api: Api,
) {
    suspend fun getExchanges(perPage: Int, page: Int): List<Exchanges> = apiCall {
        api.getExchanges(perPage, page)
    }
}
