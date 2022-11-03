package com.ync.basecompose.data

interface LocalDataSource {
    fun getAcesstoken(): String?

    fun saveAcessToken(acessToken: String)
}
