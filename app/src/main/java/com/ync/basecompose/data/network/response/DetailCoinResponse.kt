package com.ync.basecompose.data.network.response

import com.ync.basecompose.data.model.Platform
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by mvn-cuongle-dn
 */

@Serializable
data class DetailCoinResponse(
    @SerialName("name") val name: String = "",
    @SerialName("asset_platform_id") val assetPlatform: String = "",
    @SerialName("detail_platforms") val detailListPlatform: Map<String, Platform>? = null
)
