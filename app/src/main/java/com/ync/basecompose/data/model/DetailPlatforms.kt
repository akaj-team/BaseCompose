package com.ync.basecompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by mvn-cuongle-dn
 */

@Serializable
data class DetailPlatforms(
    @SerialName("ethereum") val ethereum: Platform,
    @SerialName("binance-smart-chain") val binanceSmartChain: Platform,
    @SerialName("polygon-pos") val polygonPos: Platform,
    @SerialName("energi") val energi: Platform
)
