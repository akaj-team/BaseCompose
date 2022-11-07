package com.ync.basecompose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author mvn-toan.nguyen2 on 11/4/22
 **/
@Serializable
data class Exchanges(
    @SerialName("id")
    var id: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("year_established")
    var yearEstablished: Int? = null,
    @SerialName("country")
    var country: String? = null,
    @SerialName("description")
    var description: String? = null,
    @SerialName("url")
    var url: String? = null,
    @SerialName("image")
    var imageUrl: String? = null,
    @SerialName("has_trading_incentive")
    var hasTradingIncentive: Boolean? = null,
    @SerialName("trust_score")
    var trustScore: Int? = null,
    @SerialName("trust_score_rank")
    var trustScoreRank: Int? = null,
    @SerialName("trade_volume_24h_btc")
    var tradeVolume24hBtc: Float? = null,
    @SerialName("trade_volume_24h_btc_normalized")
    var tradeVolume24hBtcNormalized: Float? = null,
) {
}
