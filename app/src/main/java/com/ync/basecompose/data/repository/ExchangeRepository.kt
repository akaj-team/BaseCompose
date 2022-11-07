package com.ync.basecompose.data.repository

import com.ync.basecompose.arch.extentions.safeFlow
import com.ync.basecompose.data.remote.ExchangeRemoteDataSource
import javax.inject.Inject

/**
 * @author mvn-toan.nguyen2 on 11/4/22
 **/
class ExchangeRepository @Inject constructor(private val exchangeRemoteDataSource: ExchangeRemoteDataSource) {
    fun getExchanges(perPage: Int, page: Int) = safeFlow {
        exchangeRemoteDataSource.getExchanges(perPage, page)
    }
}
