package com.ync.basecompose.data.network.response

import com.ync.basecompose.data.model.Error
import kotlinx.serialization.Serializable

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@Serializable
data class ErrorResponse(var message: String?, var errors: List<Error>? = null)