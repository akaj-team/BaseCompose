package com.ync.basecompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@Serializable
data class Coin(@SerialName("item") val item: Item)