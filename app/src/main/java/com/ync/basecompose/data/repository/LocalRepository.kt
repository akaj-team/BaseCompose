package com.ync.basecompose.data.repository

import android.content.SharedPreferences
import com.ync.basecompose.data.LocalDataSource
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalDataSource {
    companion object {
        private const val K_NAME = "k_name"
    }

    override fun getName(): String? {
        return sharedPreferences.getString(K_NAME, "")

    }

    override fun saveName(name: String) {
        with(sharedPreferences.edit()) {
            putString(K_NAME, name)
            apply()
        }
    }
}
