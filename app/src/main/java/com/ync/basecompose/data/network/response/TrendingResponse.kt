package com.ync.basecompose.data.network.response

import com.ync.basecompose.data.model.Coin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@Serializable
data class TrendingResponse(@SerialName("coins") val coins: List<Coin>)