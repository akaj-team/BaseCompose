package com.ync.basecompose.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ync.basecompose.BuildConfig
import com.ync.basecompose.data.network.Api
import com.ync.basecompose.data.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@InstallIn(SingletonComponent::class)
@Module
class RestModule {
    @Provides
    @Singleton
    fun provideHttpClient(
        localRepository: LocalRepository
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.interceptors().add(Interceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
            requestBuilder.addHeader("Content-Type", "application/json")
            requestBuilder.addHeader("Accept", "application/json")
            requestBuilder.addHeader("User-Agent", getUserAgent())
            requestBuilder.addHeader(
                "Authorization",
                "token ${localRepository.getAcesstoken()}"
            )
            val request = requestBuilder.build()
            chain
                .withConnectTimeout(40, TimeUnit.SECONDS)
                .withWriteTimeout(40, TimeUnit.SECONDS)
                .withReadTimeout(40, TimeUnit.SECONDS)
                .proceed(request)
        })

        clientBuilder.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }
        return clientBuilder.build()
    }

    private fun getUserAgent(): String = ""

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        jsonParser: Json
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(
                jsonParser.asConverterFactory("application/json".toMediaType())
            ).build()
    }

    @Provides
    @Singleton
    fun provideJsonParser(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}