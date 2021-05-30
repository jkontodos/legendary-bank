package com.javierkontos.legendarybank.data.server.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.javierkontos.legendarybank.BuildConfig
import com.javierkontos.legendarybank.data.server.GoliathService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder =
        HttpLoggingInterceptor().run {
            level =HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(this)
        }

    @Provides
    fun provideRetrofitClient(
        okHttpClientBuilder: OkHttpClient.Builder,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideService(retrofit: Retrofit): GoliathService =
        retrofit.create(GoliathService::class.java)
}