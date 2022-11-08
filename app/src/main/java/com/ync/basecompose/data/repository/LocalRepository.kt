package com.ync.basecompose.data.repository

import android.content.SharedPreferences
import com.ync.basecompose.data.LocalDataSource
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalDataSource {

    init {
        saveAcessToken("8dcc1542a16daaa11c0ed5c3b7add287fdb28ec1")
    }

    companion object {
        private const val K_ACESS_TOKEN = "k_acess_token"
    }

    override fun getAcesstoken(): String? {
        return sharedPreferences.getString(K_ACESS_TOKEN, "")
    }

    override fun saveAcessToken(acessToken: String) {
        with(sharedPreferences.edit()) {
            putString(K_ACESS_TOKEN, acessToken)
            apply()
        }
    }
}
