package com.ync.basecompose.data.repository

import com.ync.basecompose.arch.extentions.safeFlow
import com.ync.basecompose.data.remote.SearchRemoteDataSource
import javax.inject.Inject

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
class SearchRepository @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) {
    fun getTrending() = safeFlow {
        searchRemoteDataSource.getTrending()
    }

    fun getDetailCoin(id: String) = safeFlow {
        searchRemoteDataSource.getDetailCoin(id)
    }
}