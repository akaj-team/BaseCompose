package com.ync.basecompose.data.network.response

import com.ync.basecompose.data.model.DetailPlatforms
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by mvn-cuongle-dn
 */

@Serializable
data class DetailCoinResponse(
    @SerialName("name") val name: String? = null,
    @SerialName("asset_platform_id") val assetPlatform: String? = null,
    @SerialName("detail_platforms") val detailListPlatform: DetailPlatforms? = null
)
