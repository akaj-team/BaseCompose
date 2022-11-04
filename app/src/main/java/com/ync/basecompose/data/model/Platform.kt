package com.ync.basecompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by mvn-cuongle-dn
 */
@Serializable
data class Platform(
    @SerialName("decimal_place") val decimalPlace: Int,
    @SerialName("contract_address") val contractAddress: String,
)
