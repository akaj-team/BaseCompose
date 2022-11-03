package com.ync.basecompose.data

interface LocalDataSource {
    fun getName(): String?

    fun saveName(name: String)
}
