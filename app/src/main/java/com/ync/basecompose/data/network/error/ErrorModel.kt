package com.ync.basecompose.data.network.error

import java.net.HttpURLConnection

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
sealed class ErrorModel : Throwable() {
    open fun isCommonError(): Boolean = false

    open fun isUnAuthorized(): Boolean = false

    open fun getErrorCode(): String? = ""

    open fun getUrl(): String = ""

    open fun isForceUpdate() = false

    open fun isConsultingTime() = false

    open fun isInternalServerError() = false

    sealed class Http : ErrorModel() {
        data class ApiError(
            val code: String?,
            override val message: String?,
            val apiUrl: String?,
        ) : Http() {
            override fun isCommonError(): Boolean {
                if (code == HttpURLConnection.HTTP_UNAUTHORIZED.toString()
                    || code == HttpURLConnection.HTTP_INTERNAL_ERROR.toString()
                    || code == ApiErrorDetailCode.SERVER_MAINTENANCE_9001.code
                    || code == ApiErrorDetailCode.FORCE_UPDATE.code
                    || code == ApiErrorDetailCode.NO_RESPONSE_FROM_SERVER_9002.code
                ) {
                    return true
                }
                return false
            }

            override fun isUnAuthorized(): Boolean =
                code == HttpURLConnection.HTTP_UNAUTHORIZED.toString()

            override fun getErrorCode() = code

            override fun getUrl(): String = apiUrl ?: ""

            override fun isForceUpdate(): Boolean = code == ApiErrorDetailCode.FORCE_UPDATE.code

            override fun isInternalServerError(): Boolean =
                code == HttpURLConnection.HTTP_INTERNAL_ERROR.toString()

            override fun isConsultingTime(): Boolean =
                code == ApiErrorDetailCode.CONSULTING_TIME_ERROR.code
        }
    }

    data class LocalError(override val message: String, val code: String?) : ErrorModel()

    enum class LocalErrorException(val message: String, val code: String) {
        NO_INTERNET_EXCEPTION("インターネットに接続できません。接続を確認してください。", "1001"),
        REQUEST_TIME_OUT_EXCEPTION("読み込みに時間がかかっています。時間をおいて再度実行してください。", "1002"),
        UN_KNOW_EXCEPTION("予期せぬエラーです。", "1000")
    }

    enum class ApiErrorDetailCode(val code: String) {
        SERVER_MAINTENANCE_9001("9001"),
        FORCE_UPDATE("426"),
        NO_RESPONSE_FROM_SERVER_9002("9002"),
        TOO_MANY_REQUEST("429"),
        CONSULTING_TIME_ERROR("423")
    }
}
